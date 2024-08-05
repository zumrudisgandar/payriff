package com.payriff.payment_ms.client;

import com.payriff.payment_ms.entity.Transaction;
import com.payriff.payment_ms.enums.TransactionStatus;
import com.payriff.payment_ms.response.CreateOrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "dictionary-ms", url = "http://localhost:8081")
public interface DictionaryFeignClient {
    @PostMapping("/api/v2/saveTransaction")
    Transaction saveTransaction(@RequestBody Transaction transaction);

    @PutMapping("/api/v2/updateStatus")
    void updateTransactionStatus(@RequestParam String orderId,
                                 @RequestParam TransactionStatus status,
                                 @RequestParam String sessionId);
}
