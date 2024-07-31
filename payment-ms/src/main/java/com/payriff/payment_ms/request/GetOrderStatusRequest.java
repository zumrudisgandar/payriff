package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class GetOrderStatusRequest {
    private GetOrderStatusRequestBody body;
    private String merchant;

    @Data
    public static class GetOrderStatusRequestBody {
        private String orderId;
        private String sessionId;
        private String language;
    }
}