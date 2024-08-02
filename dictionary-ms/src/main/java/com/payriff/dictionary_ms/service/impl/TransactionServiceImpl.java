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

    public Transaction saveTransaction(TransactionDto transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.getPayload().setOrderId(transactionDTO.getPayload().getOrderId());
        transaction.getPayload().setSessionId(transactionDTO.getPayload().getSessionId());
        transaction.getPayload().setPaymentUrl(transactionDTO.getPayload().getPaymentUrl());

        return transactionRepository.save(transaction);
    }

//    public void updateTransactionStatus(Integer transactionId,
//                                        String status,
//                                        String sessionId) {
//        Transaction transaction = transactionRepository.findByTransactionId(transactionId);
//        if (transaction != null) {
//            transaction.setStatus(status);
//            transaction.getPayload().setSessionId(sessionId);
//            transactionRepository.save(transaction);
//        } else {
//        throw new TransactionNotFoundException("Transaction with orderId " + orderId + " not found.");
//        }
//    }

//
//    private TransactionDto toDTO(Transaction transaction) {
//        TransactionDto dto = new TransactionDto();
//        dto.setOrderId(transaction.getPayload().getOrderId());
//        dto.setSessionId(transaction.getPayload().getSessionId());
//        dto.setSessionId(transaction.getPayload().);
//        return dto;
//    }
}
