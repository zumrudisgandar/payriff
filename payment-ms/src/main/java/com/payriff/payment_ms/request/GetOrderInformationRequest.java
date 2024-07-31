package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class GetOrderInformationRequest {
    private GetOrderInformationRequestBody body;
    private String merchant;

    @Data
    public static class GetOrderInformationRequestBody {
        private String orderId;
        private String sessionId;
        private String language;
    }
}