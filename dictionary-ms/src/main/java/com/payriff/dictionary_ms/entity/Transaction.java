package com.payriff.dictionary_ms.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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