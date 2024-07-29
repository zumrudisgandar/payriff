package com.payriff.payriff_ms.controller;

import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class PayriffController {
    private final PayriffService payriffService;

    public PayriffController(PayriffService payriffService) {
        this.payriffService = payriffService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody CreateOrderRequest request) {
        String paymentUrl = payriffService.createOrder(request);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", paymentUrl)
                .body("Redirecting to: " + paymentUrl);
    }
}
