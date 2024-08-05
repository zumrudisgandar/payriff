package com.payriff.payriff_ms.service;

import com.payriff.payriff_ms.request.*;
import com.payriff.payriff_ms.response.*;

public interface PayriffService {
    CreateOrderResponse createOrder(CreateOrderRequest request);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);
    PreAuthResponse preAuth (PreAuthRequest preAuthRequest);
    ReverseResponse reverse (ReverseRequest reverseRequest);
    CompleteOrderResponse completeOrder (CompleteOrderRequest completeOrderRequest);
}
