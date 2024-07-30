package com.payriff.dictionary_ms.service;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.response.TransactionResponse;

public interface TransactionService {
    TransactionDto saveTransaction(TransactionDto transactionDTO);
    void updateTransactionStatus(String orderId, String status, String sessionId);
}