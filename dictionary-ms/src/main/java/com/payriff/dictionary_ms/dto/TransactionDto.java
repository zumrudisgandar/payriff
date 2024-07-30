package com.payriff.dictionary_ms.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String orderId;
    private String status;
    private String sessionId;
    private int amount;
}