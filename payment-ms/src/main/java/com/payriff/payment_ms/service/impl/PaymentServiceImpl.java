package com.payriff.payment_ms.service.impl;

import com.payriff.payment_ms.client.DictionaryFeignClient;
import com.payriff.payment_ms.client.PayriffFeignClient;
import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import com.payriff.payment_ms.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final DictionaryFeignClient dictionaryFeignClient;
    private final PayriffFeignClient payriffFeignClient;

    public PaymentServiceImpl(DictionaryFeignClient dictionaryFeignClient, PayriffFeignClient payriffFeignClient) {
        this.dictionaryFeignClient = dictionaryFeignClient;
        this.payriffFeignClient = payriffFeignClient;
    }

    public String handleApprovedPayment(String orderId, String sessionId) {
        try {
            dictionaryFeignClient.updateTransactionStatus(orderId, "APPROVED", sessionId);
            return "Payment Approved. Order ID: " + orderId + ", Session ID: " + sessionId;
        } catch (Exception e) {
            return "Failed to update approved payment: " + e.getMessage();
        }
    }

    public String handleCanceledPayment(String orderId, String sessionId) {
        try {
            dictionaryFeignClient.updateTransactionStatus(orderId, "CANCELED", sessionId);
            return "Payment Canceled. Order ID: " + orderId + ", Session ID: " + sessionId;
        } catch (Exception e) {
            return "Failed to update canceled payment: " + e.getMessage();
        }
    }

    public String handleDeclinedPayment(String orderId, String sessionId) {
        try {
            dictionaryFeignClient.updateTransactionStatus(orderId, "DECLINED", sessionId);
            return "Payment Declined. Order ID: " + orderId + ", Session ID: " + sessionId;
        } catch (Exception e) {
            return "Failed to update declined payment: " + e.getMessage();
        }
    }

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        return payriffFeignClient.createOrder(createOrderRequest);
    }
}
