package com.payriff.payriff_ms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.response.CreateOrderResponse;
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
}
