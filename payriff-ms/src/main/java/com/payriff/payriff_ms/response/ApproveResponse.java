package com.payriff.payriff_ms.response;

import lombok.Data;

@Data
public class ApproveResponse {

    private String code;
    private String message;
    private String route;
    private Payload payload;

    @Data
    public static class Payload {
        private String version;
        private Integer orderID;
        private String sessionId;
        private String transactionType;
        private String RRN;
        private String PAN;
        private Integer purchaseAmount;
        private String currency;
        private String tranDateTime;
        private String responseCode;
        private String responseDescription;
        private String brand;
        private String orderStatus;
        private String approvalCode;
        private String orderDescription;
        private String approvalCodeScr;
        private Integer purchaseAmountScr;
        private String currencyScr;
        private String orderStatusScr;
        private CardRegistration cardRegistration;
        private String invoiceUuid;

        @Data
        public static class CardRegistration {
            private String maskedPAN;
            private String cardUID;
            private String brand;
        }
    }
}
