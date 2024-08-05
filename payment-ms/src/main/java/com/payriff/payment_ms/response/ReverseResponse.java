package com.payriff.payment_ms.response;

import lombok.Data;

@Data
public class ReverseResponse {
    private String code;
    private String internalMessage;
    private String message;
}
