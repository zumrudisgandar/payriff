package com.payriff.payriff_ms.response;

import lombok.Data;

@Data
public class RefundResponse {
    private String code;
    private String internalMessage;
    private String message;
}