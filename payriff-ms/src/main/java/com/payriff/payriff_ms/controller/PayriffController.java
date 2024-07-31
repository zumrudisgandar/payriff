package com.payriff.payriff_ms.controller;

import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.request.GetOrderInformationRequest;
import com.payriff.payriff_ms.request.GetOrderStatusRequest;
import com.payriff.payriff_ms.request.RefundRequest;
import com.payriff.payriff_ms.response.GetOrderInformationResponse;
import com.payriff.payriff_ms.response.GetOrderStatusResponse;
import com.payriff.payriff_ms.response.RefundResponse;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class PayriffController {
    private final PayriffService payriffService;

    public PayriffController(PayriffService payriffService) {
        this.payriffService = payriffService;
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderRequest request) {
        String paymentUrl = payriffService.createOrder(request);
        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", paymentUrl)
//                .body("Redirecting to: " + paymentUrl);
                .build();
    }

    @PostMapping("/getOrderInformation")
    public ResponseEntity<GetOrderInformationResponse> getOrderInformation (@RequestBody GetOrderInformationRequest
                                                                                    getOrderInformationRequest) {
        GetOrderInformationResponse response = payriffService.getOrderInformation(getOrderInformationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/getOrderStatus")
    public ResponseEntity<GetOrderStatusResponse> getOrderStatus (@RequestBody GetOrderStatusRequest
                                                                          GetOrderStatusRequest) {
        GetOrderStatusResponse response = payriffService.getOrderStatus(GetOrderStatusRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundResponse> refund (@RequestBody RefundRequest refundRequest) {
        RefundResponse response = payriffService.refund(refundRequest);
        return ResponseEntity.ok(response);
    }
}
