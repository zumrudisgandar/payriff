package com.payriff.payment_ms.service;

import com.payriff.payment_ms.request.*;
import com.payriff.payment_ms.response.*;

public interface PaymentService {
    String handleApprovedPayment(String orderId, String sessionId);
    String handleCanceledPayment();
    String handleDeclinedPayment(String orderId, String sessionId);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);
    String saveTransaction(CreateOrderResponse createOrderResponse);
    PreAuthResponse preAuth (PreAuthRequest preAuthRequest);
    ReverseResponse reverse (ReverseRequest reverseRequest);
    CompleteOrderResponse completeOrder (CompleteOrderRequest completeOrderRequest);
    String saveTransaction(CreateOrderResponse createOrderResponse, String orderStatus);
}
