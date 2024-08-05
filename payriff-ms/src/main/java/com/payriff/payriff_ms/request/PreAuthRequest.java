package com.payriff.payriff_ms.request;

import lombok.Data;

@Data
public class PreAuthRequest {
    private PreAuthRequestBody body;
    private String merchant;

    @Data
    private static class PreAuthRequestBody {
        private double amount;
        private String approveUrl;
        private String cancelUrl;
        private String cardUuid;
        private String currencyType;
        private String declineUrl;
        private String description;
        private boolean directPay;
        private Integer installmentPeriod;
        private String installmentProductType;
        private String language;
        private String senderCardUuid;
    }
}
