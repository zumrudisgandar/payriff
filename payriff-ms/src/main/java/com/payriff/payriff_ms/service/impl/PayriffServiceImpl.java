package com.payriff.payriff_ms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.client.RestTemplate;


@Service
public class PayriffServiceImpl implements PayriffService {

    @Value("${payriff.api.url}")
    private String payriffApiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PayriffServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public String createOrder(CreateOrderRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<CreateOrderRequest> entity = new HttpEntity<>(request, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/createOrder",
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                CreateOrderResponse createOrderResponse = objectMapper.readValue(response.getBody(), CreateOrderResponse.class);
                return createOrderResponse.getPayload().getPaymentUrl();
            } else {
                throw new RuntimeException("Failed to create order: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while creating order", e);
        }
    }

    public GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

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

    public GetOrderStatusResponse getOrderStatus (GetOrderStatusRequest getOrderStatusRequest) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<GetOrderStatusRequest> entity = new HttpEntity<>(getOrderStatusRequest, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    payriffApiUrl + "/getOrderStatus",
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
