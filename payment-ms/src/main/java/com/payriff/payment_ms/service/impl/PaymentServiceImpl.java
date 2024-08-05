package com.payriff.payment_ms.service.impl;

import com.payriff.payment_ms.client.DictionaryFeignClient;
import com.payriff.payment_ms.client.PayriffFeignClient;
import com.payriff.payment_ms.enums.TransactionStatus;
import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.request.GetOrderInformationRequest;
import com.payriff.payment_ms.request.GetOrderStatusRequest;
import com.payriff.payment_ms.request.RefundRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import com.payriff.payment_ms.response.GetOrderInformationResponse;
import com.payriff.payment_ms.response.GetOrderStatusResponse;
import com.payriff.payment_ms.response.RefundResponse;
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
            dictionaryFeignClient.updateTransactionStatus(orderId, TransactionStatus.APPROVED, sessionId);
            return "Payment Approved. Order ID: " + orderId + ", Session ID: " + sessionId;
        } catch (Exception e) {
            return "Failed to update approved payment: " + e.getMessage();
        }
    }

    public String handleCanceledPayment() {
//        try {
//            dictionaryFeignClient.updateTransactionStatus(orderId, TransactionStatus.CANCELLED, sessionId);
//            return "Payment Canceled. Order ID: " + orderId + ", Session ID: " + sessionId;
//        } catch (Exception e) {
//            return "Failed to update canceled payment: " + e.getMessage();
//        }
        return "Payment Cancelled";
    }

    public String handleDeclinedPayment(String orderId, String sessionId) {
        try {
            dictionaryFeignClient.updateTransactionStatus(orderId, TransactionStatus.DECLINED, sessionId);
            return "Payment Declined. Order ID: " + orderId + ", Session ID: " + sessionId;
        } catch (Exception e) {
            return "Failed to update declined payment: " + e.getMessage();
        }
    }

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        return payriffFeignClient.createOrder(createOrderRequest);
    }

    public GetOrderInformationResponse getOrderInformation (GetOrderInformationRequest getOrderInformationRequest) {
        return payriffFeignClient.getOrderInformation(getOrderInformationRequest);
    }

    public GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest) {
        return payriffFeignClient.getStatusOrder(getOrderStatusRequest);
    }

    public RefundResponse refund (RefundRequest refundRequest) {
        return payriffFeignClient.refund(refundRequest);
    }

    public String saveTransaction(CreateOrderResponse createOrderResponse, String orderStatus) {
        try {
            System.out.println("MANUAL TEST: " + createOrderResponse + orderStatus);
            dictionaryFeignClient.saveTransaction(createOrderResponse, orderStatus);
            return "Transaction saved: " + createOrderResponse + orderStatus;
        } catch (Exception e) {
            return "Failed to update approved payment: " + e.getMessage();
        }
    }
}
