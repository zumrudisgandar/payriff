package com.payriff.payment_ms.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateOrderResponse {
    private String code;
    private String internalMessage;
    private String message;
    private Payload payload;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        @JsonProperty("orderId")
        private String orderId;

        @JsonProperty("sessionId")
        private String sessionId;

        @JsonProperty("paymentUrl")
        private String paymentUrl;
    }
}
