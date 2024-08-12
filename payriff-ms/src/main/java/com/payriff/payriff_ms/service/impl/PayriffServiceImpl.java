package com.payriff.payriff_ms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payriff.payriff_ms.client.PaymentFeignClient;
import com.payriff.payriff_ms.request.*;
import com.payriff.payriff_ms.response.*;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;


@Service
public class PayriffServiceImpl implements PayriffService {

    @Value("${payriff.api.url}")
    private String payriffApiUrl;

    @Value("${payriff.secret-key}")
    private String secretKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final PaymentFeignClient paymentFeignClient;

    public PayriffServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper,
                              PaymentFeignClient paymentFeignClient) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.paymentFeignClient = paymentFeignClient;
    }

    public CreateOrderResponse order(CreateOrderRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<CreateOrderRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/createOrder",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                CreateOrderResponse createOrderResponse = objectMapper.readValue(response.getBody(),
                        CreateOrderResponse.class);

                GetOrderStatusRequest getOrderStatusRequest = getGetOrderStatusRequest(request, createOrderResponse);
                GetOrderStatusResponse.Payload getOrderStatusResponse = getStatusOrder(getOrderStatusRequest).getPayload();

                CreateOrderResponseDto createOrderResponseDto = new CreateOrderResponseDto();
                CreateOrderResponseDto.Payload payload = new CreateOrderResponseDto.Payload();
                createOrderResponseDto.setMessage(createOrderResponse.getMessage());
                createOrderResponseDto.setCode(createOrderResponse.getCode());
                createOrderResponseDto.setInternalMessage(createOrderResponse.getInternalMessage());

                payload.setPaymentUrl(createOrderResponse.getPayload().getPaymentUrl());
                payload.setSessionId(createOrderResponse.getPayload().getSessionId());
                payload.setOrderId(createOrderResponse.getPayload().getOrderId());
                payload.setOrderStatus(getOrderStatusResponse.getOrderStatus());

                createOrderResponseDto.setPayload(payload);

                // Save the transaction with the initial status
                paymentFeignClient.saveTransaction(createOrderResponseDto);
                System.out.println("Initial transaction saved successfully");

                // Start background status update check
                CompletableFuture.runAsync(() -> updateOrderStatus(request, createOrderResponseDto, createOrderResponse));

                return createOrderResponse;
            } else {
                throw new RuntimeException("Failed to create order: " + response.getStatusCode());
            }
        } catch (Exception e) {
            if (e instanceof HttpClientErrorException) {
                HttpClientErrorException httpClientErrorException = (HttpClientErrorException) e;
            }
            throw new RuntimeException("Exception occurred while creating order", e);
        }
    }

    private void updateOrderStatus(CreateOrderRequest request, CreateOrderResponseDto createOrderResponseDto, CreateOrderResponse createOrderResponse) {
        try {
            // Poll for the updated status
            GetOrderStatusRequest getOrderStatusRequest = getGetOrderStatusRequest(request, createOrderResponse);
            GetOrderStatusResponse.Payload getOrderStatusResponsePayload;
            String orderStatus;
            do {
                getOrderStatusResponsePayload = getStatusOrder(getOrderStatusRequest).getPayload();
                orderStatus = getOrderStatusResponsePayload.getOrderStatus();
                Thread.sleep(5000);
            } while ("CREATED".equals(orderStatus));

            createOrderResponseDto.getPayload().setOrderStatus(orderStatus);

            // Save the updated transaction
            paymentFeignClient.updateTransaction(createOrderResponseDto);
            System.out.println("Transaction updated successfully with new status");

        } catch (Exception e) {
            System.err.println("Error while updating order status: " + e.getMessage());
        }
    }

    private static GetOrderStatusRequest getGetOrderStatusRequest(CreateOrderRequest request, CreateOrderResponse createOrderResponse) {
        GetOrderStatusRequest getOrderStatusRequest = new GetOrderStatusRequest();
        GetOrderStatusRequest.GetOrderStatusRequestBody getOrderStatusRequestBody = new
                GetOrderStatusRequest.GetOrderStatusRequestBody();

        getOrderStatusRequest.setMerchant(request.getMerchant());
        getOrderStatusRequestBody.setOrderId(createOrderResponse.getPayload().getOrderId());
        getOrderStatusRequestBody.setSessionId(createOrderResponse.getPayload().getSessionId());
        getOrderStatusRequestBody.setLanguage(request.getBody().getLanguage());

        getOrderStatusRequest.setBody(getOrderStatusRequestBody);
        return getOrderStatusRequest;
    }

    public GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<GetOrderInformationRequest> entity = new HttpEntity<>(getOrderInformationRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/getOrderInformation",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), GetOrderInformationResponse.class);
            } else {
                throw new RuntimeException("Failed to get order information: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while getting order information", e);
        }
    }

    public GetOrderStatusResponse getStatusOrder(GetOrderStatusRequest getOrderStatusRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<GetOrderStatusRequest> entity = new HttpEntity<>(getOrderStatusRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/getStatusOrder",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), GetOrderStatusResponse.class);
            } else {
                throw new RuntimeException("Failed to get order status: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occured while getting order status", e);
        }
    }

    public RefundResponse refund(RefundRequest refundRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<RefundRequest> entity = new HttpEntity<>(refundRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/refund",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), RefundResponse.class);
            } else {
                throw new RuntimeException("Failed to refund: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occured during refund process", e);
        }
    }

    @Override
    public ReverseResponse reverse(ReverseRequest reverseRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "B1B6A686E098423AA64552915E611B49");

            HttpEntity<ReverseRequest> entity = new HttpEntity<>(reverseRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/reverse",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), ReverseResponse.class);
            } else {
                throw new RuntimeException("Failed to reverse: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred during reverse process", e);
        }
    }

    @Override
    public CardSaveResponse cardSave(CardSaveRequest cardSaveRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<CardSaveRequest> entity = new HttpEntity<>(cardSaveRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/cardSave",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), CardSaveResponse.class);
            } else {
                throw new RuntimeException("Failed to card save: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while card saving", e);
        }
    }

    @Override
    public AutoPayResponse autoPay(AutoPayRequest autoPayRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", secretKey);

            HttpEntity<AutoPayRequest> entity = new HttpEntity<>(autoPayRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/autoPay",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return objectMapper.readValue(response.getBody(), AutoPayResponse.class);
            } else {
                throw new RuntimeException("Failed to auto pay: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while auto paying", e);
        }
    }

}
