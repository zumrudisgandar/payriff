package com.payriff.payment_ms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    private String code;
    private String internalMessage;
    private String message;
    private Payload payload;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        @JsonProperty("orderId")
        private String orderId;

        @JsonProperty("paymentUrl")
        private String paymentUrl;

        @JsonProperty("sessionId")
        private String sessionId;
    }
}
