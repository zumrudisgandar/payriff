package com.payriff.dictionary_ms.controller;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/saveTransaction")
    public ResponseEntity<TransactionDto> saveTransaction(@RequestBody TransactionDto transactionDto) {
        TransactionDto savedTransaction = transactionService.saveTransaction(transactionDto);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Void> updateTransactionStatus(
            @RequestParam String orderId,
            @RequestParam String status,
            @RequestParam String sessionId) {
        transactionService.updateTransactionStatus(orderId, status, sessionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
