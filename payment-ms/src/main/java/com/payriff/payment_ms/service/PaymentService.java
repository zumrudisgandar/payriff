package com.payriff.payment_ms.service;

import com.payriff.payment_ms.entity.Transaction;
import com.payriff.payment_ms.request.*;
import com.payriff.payment_ms.response.*;

public interface PaymentService {
    String handleCanceledPayment();
    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);
    PreAuthResponse preAuth (PreAuthRequest preAuthRequest);
    ReverseResponse reverse (ReverseRequest reverseRequest);
    CompleteOrderResponse completeOrder (CompleteOrderRequest completeOrderRequest);
    String saveTransaction(Transaction transaction);
    String updateTransaction(Transaction transaction);
}
