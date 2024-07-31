package com.payriff.payriff_ms.request;

import lombok.Data;

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
