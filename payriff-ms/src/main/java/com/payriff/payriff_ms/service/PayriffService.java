package com.payriff.payriff_ms.service;

import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.request.GetOrderInformationRequest;
import com.payriff.payriff_ms.request.GetOrderStatusRequest;
import com.payriff.payriff_ms.request.RefundRequest;
import com.payriff.payriff_ms.response.GetOrderInformationResponse;
import com.payriff.payriff_ms.response.GetOrderStatusResponse;
import com.payriff.payriff_ms.response.RefundResponse;

public interface PayriffService {
    String createOrder(CreateOrderRequest request);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getOrderStatus (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);

}
