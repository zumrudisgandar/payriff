package com.payriff.payriff_ms.service;

import com.payriff.payriff_ms.request.CreateOrderRequest;

public interface PayriffService {
    String createOrder(CreateOrderRequest request);
}
