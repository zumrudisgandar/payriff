package com.payriff.payment_ms.client;

import com.payriff.payment_ms.request.CreateOrderRequest;
import com.payriff.payment_ms.request.GetOrderInformationRequest;
import com.payriff.payment_ms.request.GetOrderStatusRequest;
import com.payriff.payment_ms.request.RefundRequest;
import com.payriff.payment_ms.response.CreateOrderResponse;
import com.payriff.payment_ms.response.GetOrderInformationResponse;
import com.payriff.payment_ms.response.GetOrderStatusResponse;
import com.payriff.payment_ms.response.RefundResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payriff-ms",
        url = "http://localhost:9595/api/v2")
public interface PayriffFeignClient {

    @PostMapping("/createOrder")
    CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest);

    @PostMapping("/getOrderInformation")
    GetOrderInformationResponse getOrderInformation (@RequestBody GetOrderInformationRequest
                                                             getOrderInformationRequest);

    @PostMapping("/getStatusOrder")
    GetOrderStatusResponse getStatusOrder (@RequestBody GetOrderStatusRequest getOrderStatusRequest);

    @PostMapping("/refund")
    RefundResponse refund (@RequestBody RefundRequest refundRequest);
}
