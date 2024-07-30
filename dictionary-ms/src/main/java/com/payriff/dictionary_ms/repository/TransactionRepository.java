package com.payriff.dictionary_ms.repository;

import com.payriff.dictionary_ms.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    Transaction findByOrderId(String orderId);
}
