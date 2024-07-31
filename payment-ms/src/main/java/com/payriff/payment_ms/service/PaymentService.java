package com.payriff.payment_ms.service;

import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.request.GetOrderInformationRequest;
import com.payriff.payment_ms.request.GetOrderStatusRequest;
import com.payriff.payment_ms.request.RefundRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import com.payriff.payment_ms.response.GetOrderInformationResponse;
import com.payriff.payment_ms.response.GetOrderStatusResponse;
import com.payriff.payment_ms.response.RefundResponse;

public interface PaymentService {
    String handleApprovedPayment(String orderId, String sessionId);
    String handleCanceledPayment(String orderId, String sessionId);
    String handleDeclinedPayment(String orderId, String sessionId);
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getOrderStatus (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);
}
