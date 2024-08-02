package com.payriff.dictionary_ms.repository;

import com.payriff.dictionary_ms.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByTransactionId(Integer transactionId);
}
