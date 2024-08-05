package com.payriff.dictionary_ms.controller;

import com.payriff.dictionary_ms.dto.TransactionDto;
import com.payriff.dictionary_ms.entity.Transaction;
import com.payriff.dictionary_ms.service.TransactionService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/saveTransaction")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionDto transactionDto) {
        System.out.println("hello");
        return ResponseEntity.ok(transactionService.saveTransaction(transactionDto));
    }

//    @PutMapping("/updateStatus")
//    public ResponseEntity<Void> updateTransactionStatus(
//            @RequestParam String orderId,
//            @RequestParam String status,
//            @RequestParam String sessionId) {
//        transactionService.updateTransactionStatus(orderId, status, sessionId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
