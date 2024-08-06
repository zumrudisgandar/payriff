package com.payriff.payriff_ms.service;

import com.payriff.payriff_ms.request.*;
import com.payriff.payriff_ms.response.*;

public interface PayriffService {
    CreateOrderResponse order(CreateOrderRequest request);
    GetOrderInformationResponse getOrderInformation(GetOrderInformationRequest getOrderInformationRequest);
    GetOrderStatusResponse getStatusOrder (GetOrderStatusRequest getOrderStatusRequest);
    RefundResponse refund (RefundRequest refundRequest);
    ReverseResponse reverse (ReverseRequest reverseRequest);
    CardSaveResponse cardSave(CardSaveRequest cardSaveRequest);
    AutoPayResponse autoPay(AutoPayRequest autoPayRequest);
}
