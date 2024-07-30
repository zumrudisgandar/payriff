package com.payriff.dictionary_ms.service.impl;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.entity.Transaction;
import com.payriff.dictionary_ms.exception.TransactionNotFoundException;
import com.payriff.dictionary_ms.repository.TransactionRepository;
import com.payriff.dictionary_ms.response.TransactionResponse;
import com.payriff.dictionary_ms.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository) throws TransactionNotFoundException {
        this.transactionRepository = transactionRepository;
    }

    public TransactionDto saveTransaction(TransactionDto transactionDTO) {
        // Convert DTO to Entity
        Transaction transaction = new Transaction();
        // Set properties from DTO to Entity
        transaction.setOrderId(transactionDTO.getOrderId());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setSessionId(transactionDTO.getSessionId());
        transaction.setAmount(transactionDTO.getAmount());

        // Save to repository
        Transaction savedTransaction = transactionRepository.save(transaction);

        return toDTO(savedTransaction);
    }

    public void updateTransactionStatus(String orderId,
                                        String status,
                                        String sessionId) {
        Transaction transaction = transactionRepository.findByOrderId(orderId);
        if (transaction != null) {
            transaction.setStatus(status);
            transaction.setSessionId(sessionId);
            transactionRepository.save(transaction);
        } else {
        throw new TransactionNotFoundException("Transaction with orderId " + orderId + " not found.");
        }
    }


    private TransactionDto toDTO(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setOrderId(transaction.getOrderId());
        dto.setStatus(transaction.getStatus());
        dto.setSessionId(transaction.getSessionId());
        dto.setAmount(transaction.getAmount());
        return dto;
    }
}
