package com.payriff.payment_ms.controller;

import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import com.payriff.payment_ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/transaction/approved")
    public ResponseEntity<String> paymentApproved(@RequestParam String orderId, @RequestParam String sessionId) {
        String response = paymentService.handleApprovedPayment(orderId, sessionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transaction/canceled")
    public ResponseEntity<String> paymentCanceled(@RequestParam String orderId, @RequestParam String sessionId) {
        String response = paymentService.handleCanceledPayment(orderId, sessionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transaction/declined")
    public ResponseEntity<String> paymentDeclined(@RequestParam String orderId, @RequestParam String sessionId) {
        String response = paymentService.handleDeclinedPayment(orderId, sessionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResponse response = paymentService.createOrder(createOrderRequest);
        return ResponseEntity.ok(response);
    }

}
