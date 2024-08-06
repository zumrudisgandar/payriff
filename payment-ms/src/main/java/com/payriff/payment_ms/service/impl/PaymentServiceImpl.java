package com.payriff.payment_ms.service.impl;

import com.payriff.payment_ms.client.DictionaryFeignClient;
import com.payriff.payment_ms.client.PayriffFeignClient;
import com.payriff.payment_ms.entity.Transaction;
import com.payriff.payment_ms.request.*;
import com.payriff.payment_ms.response.*;
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

    public String handleCanceledPayment() {
        return "Payment Cancelled";
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

    public String saveTransaction(Transaction transaction) {
        try {
            System.out.println("MANUAL TEST: " + transaction);
            dictionaryFeignClient.saveTransaction(transaction);
            return "Transaction saved: " + transaction;
        } catch (Exception e) {
            return "Failed to update approved payment: " + e.getMessage();
        }
    }

    @Override
    public String updateTransaction(Transaction transaction) {
        try {
            dictionaryFeignClient.updateTransaction(transaction);
            return "Transaction updated: " + transaction;
        } catch (Exception e) {
            return "Failed to update transaction: " + e.getMessage();
        }
    }

    @Override
    public CardSaveResponse cardSave(CardSaveRequest cardSaveRequest) {
        return payriffFeignClient.cardSave(cardSaveRequest);
    }

    @Override
    public AutoPayResponse autoPay(AutoPayRequest autoPayRequest) {
        return payriffFeignClient.autoPay(autoPayRequest);

    }

    @Override
    public ReverseResponse reverse(ReverseRequest reverseRequest) {
        return payriffFeignClient.reverse(reverseRequest);
    }
}
