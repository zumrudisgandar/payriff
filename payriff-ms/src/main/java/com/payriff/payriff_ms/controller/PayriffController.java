package com.payriff.payriff_ms.controller;

import com.payriff.payriff_ms.client.PaymentFeignClient;
import com.payriff.payriff_ms.request.*;
import com.payriff.payriff_ms.response.*;
import com.payriff.payriff_ms.service.PayriffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v2")
public class PayriffController {

    private final PayriffService payriffService;
    private final PaymentFeignClient paymentFeignClient;

    public PayriffController(PayriffService payriffService, PaymentFeignClient paymentFeignClient) {
        this.payriffService = payriffService;
        this.paymentFeignClient = paymentFeignClient;
    }

    @PostMapping("/handleApprove")
    public ResponseEntity<ApproveResponse> handleApprove(@RequestBody ApproveResponse approveResponse) {
        try {
//            paymentFeignClient.updateTransaction(approveResponse);
//            return  ResponseEntity.ok("Transaction details saved successfully");
            return  ResponseEntity.ok(approveResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(approveResponse);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        try {
            CreateOrderResponse response = new CreateOrderResponse();

            CreateOrderResponse response1 = payriffService.createOrder(request);
            CreateOrderResponse.Payload payload = new CreateOrderResponse.Payload();
            payload.setPaymentUrl(response1.getPayload().getPaymentUrl());
            payload.setOrderId(response1.getPayload().getOrderId());
            payload.setSessionId(response1.getPayload().getSessionId());
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

    @PostMapping("/getStatusOrder")
    public ResponseEntity<GetOrderStatusResponse> getStatusOrder (@RequestBody GetOrderStatusRequest
                                                                          GetOrderStatusRequest) {
        GetOrderStatusResponse response = payriffService.getStatusOrder(GetOrderStatusRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refund")
    public ResponseEntity<RefundResponse> refund (@RequestBody RefundRequest refundRequest) {
        RefundResponse response = payriffService.refund(refundRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/preAuth")
    public ResponseEntity<PreAuthResponse> preAuth(@RequestBody PreAuthRequest request) {
        try {
            PreAuthResponse response = new PreAuthResponse();

            PreAuthResponse response1 = payriffService.preAuth(request);
            System.out.println("TEST MANUAL: " + response1);
            PreAuthResponse.Payload payload = new PreAuthResponse.Payload();
            payload.setPaymentUrl(response1.getPayload().getPaymentUrl());
            payload.setOrderId(response1.getPayload().getOrderId());
            payload.setSessionId(response1.getPayload().getSessionId());

            response.setPayload(payload);

            response.setCode("200");
            response.setMessage("Order created successfully");
            return ResponseEntity.status(OK).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("TEST MANUAL: ERROR");
            PreAuthResponse errorResponse = new PreAuthResponse();
            errorResponse.setCode("500");
            errorResponse.setMessage("Internal Server Error");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/reverse")
    public ResponseEntity<ReverseResponse> reverse(@RequestBody ReverseRequest request) {
        ReverseResponse response = payriffService.reverse(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/completeOrder")
    public ResponseEntity<CompleteOrderResponse> completeOrder(@RequestBody CompleteOrderRequest request){
        CompleteOrderResponse response = payriffService.completeOrder(request);
        return ResponseEntity.ok(response);
    }
}
