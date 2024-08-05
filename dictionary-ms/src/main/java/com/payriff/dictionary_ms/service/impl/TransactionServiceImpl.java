package com.payriff.dictionary_ms.service.impl;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.entity.Transaction;
import com.payriff.dictionary_ms.exception.TransactionNotFoundException;
import com.payriff.dictionary_ms.repository.TransactionRepository;
import com.payriff.dictionary_ms.response.TransactionResponse;
import com.payriff.dictionary_ms.service.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;


    public TransactionServiceImpl(TransactionRepository transactionRepository) throws TransactionNotFoundException {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction saveTransaction(TransactionDto transactionDTO) {
        Transaction transaction = new Transaction();
        Transaction.Payload payload = new Transaction.Payload();

        transaction.setCode(transactionDTO.getCode());
        transaction.setMessage(transactionDTO.getMessage());
        transaction.setInternalMessage(transactionDTO.getInternalMessage());
        payload.setOrderId(transactionDTO.getPayload().getOrderId());
        payload.setSessionId(transactionDTO.getPayload().getSessionId());
        payload.setPaymentUrl(transactionDTO.getPayload().getPaymentUrl());
        payload.setOrderStatus(transactionDTO.getPayload().getOrderStatus());

        transaction.setPayload(payload);

        return transactionRepository.save(transaction);
    }

    @Transactional
    public void updateTransaction(TransactionDto transactionDTO) {
        Optional<Transaction> existingTransactionOptional = transactionRepository.findByPayloadOrderId(transactionDTO.getPayload().getOrderId());

        if (existingTransactionOptional.isPresent()) {
            Transaction existingTransaction = existingTransactionOptional.get();

            existingTransaction.setCode(transactionDTO.getCode());
            existingTransaction.setMessage(transactionDTO.getMessage());
            existingTransaction.setInternalMessage(transactionDTO.getInternalMessage());

            Transaction.Payload payload = existingTransaction.getPayload();
            payload.setOrderId(transactionDTO.getPayload().getOrderId());
            payload.setSessionId(transactionDTO.getPayload().getSessionId());
            payload.setPaymentUrl(transactionDTO.getPayload().getPaymentUrl());
            payload.setOrderStatus(transactionDTO.getPayload().getOrderStatus());

            // Save the updated transaction
            transactionRepository.save(existingTransaction);
        } else {
            throw new EntityNotFoundException("Transaction with orderId " + transactionDTO.getPayload().getOrderId() + " not found");
        }
    }

//
//    private TransactionDto toDTO(Transaction transaction) {
//        TransactionDto dto = new TransactionDto();
//        dto.setOrderId(transaction.getPayload().getOrderId());
//        dto.setSessionId(transaction.getPayload().getSessionId());
//        dto.setSessionId(transaction.getPayload().);
//        return dto;
//    }
}
