package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class RefundRequest {
    private RefundRequestBody body;
    private String merchant;

    @Data
    public static class RefundRequestBody {
        private String orderId;
        private String sessionId;
        private String refundAmount;
    }
}