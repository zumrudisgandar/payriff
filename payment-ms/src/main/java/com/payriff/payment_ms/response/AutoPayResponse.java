package com.payriff.payment_ms.response;

import lombok.Data;

@Data
public class AutoPayResponse {
    private String code;
    private String internalMessage;
    private String message;
}