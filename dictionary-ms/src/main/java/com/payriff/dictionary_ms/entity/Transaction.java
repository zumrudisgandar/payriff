package com.payriff.dictionary_ms.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private String code;
    private String internalMessage;
    private String message;

    @Embedded
    private Payload payload;

    @Data
    @Embeddable
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