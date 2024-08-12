package com.payriff.payment_ms.controller;

import com.payriff.payment_ms.entity.Transaction;
import com.payriff.payment_ms.request.*;
import com.payriff.payment_ms.response.*;
import com.payriff.payment_ms.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/transaction/canceled")
    public ResponseEntity<String> paymentCanceled() {
        String response = paymentService.handleCanceledPayment();
        return ResponseEntity.ok(response);
    }


    @PostMapping("/saveTransaction")
    public ResponseEntity<String> saveTransaction(@RequestBody Transaction transaction) {
        String response = paymentService.saveTransaction(transaction);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateTransaction")
    public ResponseEntity<String> updateTransaction(@RequestBody Transaction transaction) {
        String response = paymentService.updateTransaction(transaction);
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

    @PostMapping("/reverse")
    public ResponseEntity<ReverseResponse> reverse (@RequestBody ReverseRequest request) {
        ReverseResponse response = paymentService.reverse(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cardSave")
    public ResponseEntity<CardSaveResponse> cardSave (@RequestBody CardSaveRequest cardSaveRequest) {
        CardSaveResponse response = paymentService.cardSave(cardSaveRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/autoPay")
    public ResponseEntity<AutoPayResponse> autoPay (@RequestBody AutoPayRequest autoPayRequest) {
        AutoPayResponse response = paymentService.autoPay(autoPayRequest);
        return ResponseEntity.ok(response);
    }
}
