package com.payriff.payment_ms.request;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private CreateOrderRequestBody body;
    private String merchant;

    @Data
    public static class CreateOrderRequestBody {
        private double amount;
        private String approveURL;
        private String cancelURL;
        private String cardUuid;
        private String currencyType;
        private String declineURL;
        private String description;
        private boolean directPay;
        private int installmentPeriod;
        private String installmentProductType;
        private String language;
        private String senderCardUID;
    }
}
