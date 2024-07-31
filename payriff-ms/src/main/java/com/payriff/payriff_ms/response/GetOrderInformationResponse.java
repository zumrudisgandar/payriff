package com.payriff.payriff_ms.response;

import lombok.Data;

@Data
public class GetOrderInformationResponse {

    private String code;
    private String internalMessage;
    private String message;
    private Payload payload;

    @Data
    public static class Payload {
        private Row row;

        @Data
        public static class Row {
            private String amount;
            private String approveURL;
            private String cancelURL;
            private String createDate;
            private String currency;
            private String declineURL;
            private String description;
            private String extSystemProcess;
            private String fee;
            private String id;
            private String lastUpdateDate;
            private String merchantID;
            private String orderLanguage;
            private OrderOperations orderOperations;
            private String orderType;
            private String orderstatus;
            private String payDate;
            private String receipt;
            private String refundAmount;
            private String refundCurrency;
            private String refundDate;
            private String sessionID;
            private String twoId;
            private String twodate;

            @Data
            public static class OrderOperations {
                private SubRow subRow;

                @Data
                public static class SubRow {
                }
            }
        }
    }
}