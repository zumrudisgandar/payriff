package com.payriff.payment_ms.entity;

import lombok.Data;

@Data
public class Transaction {
    private String orderId;
    private String sessionId;
    private int amount;
    private String currency;
    private String status;
    private String responseCode;
    private String responseDescription;
    private String cardUID;
    private String maskedPAN;
    private String brand;
    private String transactionType;
    private String tranDateTime;
    private String approvalCode;

}