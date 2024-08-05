package com.payriff.payment_ms.response;

import lombok.Data;

@Data
public class CompleteOrderResponse {
    private String code;
    private String InternalMessage;
    private String message;
}
