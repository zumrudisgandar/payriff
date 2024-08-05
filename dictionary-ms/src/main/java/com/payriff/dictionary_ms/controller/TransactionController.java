package com.payriff.dictionary_ms.controller;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.entity.Transaction;
import com.payriff.dictionary_ms.service.TransactionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/saveTransaction")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok(transactionService.saveTransaction(transactionDto));
    }
    @PutMapping("/updateTransaction")
    public ResponseEntity<Void> updateTransaction(
            @RequestBody TransactionDto transactionDto) {
        transactionService.updateTransaction(transactionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
