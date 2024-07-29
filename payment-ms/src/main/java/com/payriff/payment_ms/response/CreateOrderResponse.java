package com.payriff.payment_ms.response;

import lombok.Data;

@Data
public class CreateOrderResponse {
    private String code;
    private String internalMessage;
    private String message;
    private Payload payload;

    @Data
    public static class Payload {
        private String orderId;
        private String paymentUrl;
        private String sessionId;
    }
}
