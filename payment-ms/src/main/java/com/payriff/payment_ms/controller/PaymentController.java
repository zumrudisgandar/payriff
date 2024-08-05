package com.payriff.payment_ms.controller;

import com.payriff.payment_ms.entity.Transaction;
import com.payriff.payment_ms.request.*;
import com.payriff.payment_ms.response.*;
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
    public ResponseEntity<String> paymentCanceled() {
        String response = paymentService.handleCanceledPayment();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transaction/declined")
    public ResponseEntity<String> paymentDeclined(@RequestParam String orderId, @RequestParam String sessionId) {
        String response = paymentService.handleDeclinedPayment(orderId, sessionId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/saveTransaction")
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {
        String response = paymentService.saveTransaction(transaction);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        CreateOrderResponse response = paymentService.createOrder(createOrderRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getOrderInformation")
    public ResponseEntity<GetOrderInformationResponse> getOrderInformation (@RequestBody GetOrderInformationRequest
                                                                                    getOrderInformationRequest) {
        GetOrderInformationResponse response = paymentService.getOrderInformation(getOrderInformationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getStatusOrder")
    public ResponseEntity<GetOrderStatusResponse> getStatusOrder (@RequestBody GetOrderStatusRequest
                                                                          getOrderStatusRequest) {
        GetOrderStatusResponse response = paymentService.getStatusOrder(getOrderStatusRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundResponse> refundResponse (@RequestBody RefundRequest refundRequest) {
        RefundResponse response = paymentService.refund(refundRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/preAuth")
    public ResponseEntity<PreAuthResponse> preAuth (@RequestBody PreAuthRequest request) {
        PreAuthResponse response = paymentService.preAuth(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reverse")
    public ResponseEntity<ReverseResponse> reverse (@RequestBody ReverseRequest request) {
        ReverseResponse response = paymentService.reverse(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/completeOrder")
    public ResponseEntity<CompleteOrderResponse> completeOrder (@RequestBody CompleteOrderRequest request) {
        CompleteOrderResponse response = paymentService.completeOrder(request);
        return ResponseEntity.ok(response);
    }

}
