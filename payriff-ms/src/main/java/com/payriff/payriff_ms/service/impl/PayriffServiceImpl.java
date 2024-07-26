package com.payriff.payriff_ms.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.beans.factory.annotation.Value;
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


}
