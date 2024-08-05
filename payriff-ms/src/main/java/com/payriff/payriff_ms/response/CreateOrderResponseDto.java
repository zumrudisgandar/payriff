package com.payriff.payriff_ms.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateOrderResponseDto {
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

        @JsonProperty("orderStatus")
        private String orderStatus;
    }
}
