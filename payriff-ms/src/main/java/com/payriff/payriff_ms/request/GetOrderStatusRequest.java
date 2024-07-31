package com.payriff.payriff_ms.request;

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