package com.payriff.payment_ms.service;

import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;

public interface PaymentService {
    String handleApprovedPayment(String orderId, String sessionId);
    String handleCanceledPayment(String orderId, String sessionId);
    String handleDeclinedPayment(String orderId, String sessionId);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
}
