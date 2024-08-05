package com.payriff.dictionary_ms.service;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.entity.Transaction;

public interface TransactionService {
    Transaction saveTransaction(TransactionDto transactionDTO);
    void updateTransaction(TransactionDto transactionDTO);
}