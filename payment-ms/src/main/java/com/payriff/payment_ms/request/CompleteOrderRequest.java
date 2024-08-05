package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class CompleteOrderRequest {
    private CreateOrderRequestBody body;
    private String merchant;

    @Data
    public static class CreateOrderRequestBody {
        private double amount;
        private String description;
        private String Language;
        private String OrderId;
        private String sessionId;
    }
}
