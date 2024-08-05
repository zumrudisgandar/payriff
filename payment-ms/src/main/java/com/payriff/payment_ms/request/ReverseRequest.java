package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class ReverseRequest {
    private ReverseRequestBody body;
    private String merchant;

    @Data
    public static class ReverseRequestBody {
        private double amount;
        private String description;
        private String Language;
        private String orderId;
        private String sessionId;
    }
}
