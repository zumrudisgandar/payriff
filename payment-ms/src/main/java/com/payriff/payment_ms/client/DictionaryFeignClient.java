package com.payriff.payment_ms.client;

import com.payriff.payment_ms.entity.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "dictionary-ms", url = "http://localhost:8081")
public interface DictionaryFeignClient {
    @PostMapping("/api/v2/transaction/saveTransaction")
    Transaction saveTransaction(@RequestBody Transaction transaction);

    @PutMapping("/api/v2/transaction/updateStatus")
    void updateTransactionStatus(@RequestParam String orderId, @RequestParam String status, @RequestParam String sessionId);
}
