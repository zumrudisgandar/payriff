package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class AutoPayRequest {
    private AutoPayRequestBody body;
    private String merchant;

    @Data
    public static class AutoPayRequestBody {
        private double amount;
        private String cardUuid;
        private String description;
        private String orderId;
        private String sessionId;
    }
}
