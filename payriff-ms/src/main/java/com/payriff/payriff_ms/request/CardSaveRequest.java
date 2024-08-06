package com.payriff.payriff_ms.request;

import lombok.Data;

@Data
public class CardSaveRequest {
    private CardSaveRequestBody body;
    private String merchant;

    @Data
    public static class CardSaveRequestBody{
        private double amount;
        private String approveUrl;
        private String cancelUrl;
        private String declineUrl;
        private String currencyType;
        private String description;
        private boolean directPay;
        private String language;
    }
}
