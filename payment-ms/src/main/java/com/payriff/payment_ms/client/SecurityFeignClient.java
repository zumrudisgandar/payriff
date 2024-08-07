package com.payriff.payment_ms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "security-ms", url = "http://localhost:9898/api/auth")
public interface SecurityFeignClient {

    @GetMapping("/validate")
    String validateToken(@RequestParam("token") String token);
}