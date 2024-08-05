package com.payriff.payriff_ms.client;

import com.payriff.payriff_ms.response.CreateOrderResponse;
import com.payriff.payriff_ms.response.CreateOrderResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-ms",
        url = "http://localhost:9090/api/v2")
public interface PaymentFeignClient {
    @PostMapping("/saveTransaction")
    ResponseEntity<String> saveTransaction(@RequestBody CreateOrderResponseDto createOrderResponseDto);

    @PostMapping("/updateTransaction")
    ResponseEntity<String> updateTransaction(@RequestBody CreateOrderResponseDto createOrderResponseDto);
}
