package com.payriff.dictionary_ms.response;

import lombok.Data;

@Data
public class TransactionResponse {
    private Payload payload;
    private String code;
    private String message;
    private String route;

    @Data
    public static class Payload {
        private String version;
        private String orderID;
        private String sessionId;
        private String transactionType;
        private String RRN;
        private String PAN;
        private int purchaseAmount;
        private String currency;
        private String tranDateTime;
        private String responseCode;
        private String responseDescription;
        private String brand;
        private String orderStatus;
        private String approvalCode;
        private String orderDescription;
        private String approvalCodeScr;
        private int purchaseAmountScr;
        private String currencyScr;
        private String orderStatusScr;
        private CardRegistration cardRegistration;
        private String invoiceUuid;

        public Payload(String orderID, String orderStatus, String sessionId) {
            this.orderID = orderID;
            this.orderStatus = orderStatus;
            this.sessionId = sessionId;
        }

        @Data
        public static class CardRegistration {
            private String MaskedPAN;
            private String CardUID;
            private String Brand;
        }
    }


}