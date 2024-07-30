package com.payriff.payment_ms.client;

import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payriff-ms", url = "http://localhost:9595")
public interface PayriffFeignClient {
    @PostMapping("/api/v2/createOrder")
    CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest);
}
