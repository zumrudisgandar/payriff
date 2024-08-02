package com.payriff.payriff_ms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payriff.payriff_ms.client.PaymentFeignClient;
import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.request.GetOrderInformationRequest;
import com.payriff.payriff_ms.request.GetOrderStatusRequest;
import com.payriff.payriff_ms.request.RefundRequest;
import com.payriff.payriff_ms.response.CreateOrderResponse;
import com.payriff.payriff_ms.response.GetOrderInformationResponse;
import com.payriff.payriff_ms.response.GetOrderStatusResponse;
import com.payriff.payriff_ms.response.RefundResponse;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


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

    public CreateOrderResponse createOrder(CreateOrderRequest request) {
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

                paymentFeignClient.saveTransaction(createOrderResponse);
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

    public GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest) {
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

    public RefundResponse refund (RefundRequest refundRequest) {
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
}
