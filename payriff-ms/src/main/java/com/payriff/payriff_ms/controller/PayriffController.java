package com.payriff.payriff_ms.controller;

import com.payriff.payriff_ms.request.CreateOrderRequest;
import com.payriff.payriff_ms.request.GetOrderInformationRequest;
import com.payriff.payriff_ms.request.GetOrderStatusRequest;
import com.payriff.payriff_ms.request.RefundRequest;
import com.payriff.payriff_ms.response.CreateOrderResponse;
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
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
//        System.out.println("Creating order with request: " + request);
//        String paymentUrl = payriffService.createOrder(request);
//        System.out.println("TEST MANUAL: " + paymentUrl);
//        return ResponseEntity.status(HttpStatus.FOUND)
//                .header("Location", paymentUrl)
////                .body("Redirecting to: " + paymentUrl);
//                .build();
        try {
            CreateOrderResponse response = new CreateOrderResponse();

            String paymentUrl = payriffService.createOrder(request);
            System.out.println("TEST MANUAL: " + paymentUrl);
            CreateOrderResponse.Payload payload = new CreateOrderResponse.Payload();
            payload.setPaymentUrl(paymentUrl);

            response.setPayload(payload);

            response.setCode("200");
            response.setMessage("Order created successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TEST MANUAL: ERROR");
            CreateOrderResponse errorResponse = new CreateOrderResponse();
            errorResponse.setCode("500");
            errorResponse.setMessage("Internal Server Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
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
