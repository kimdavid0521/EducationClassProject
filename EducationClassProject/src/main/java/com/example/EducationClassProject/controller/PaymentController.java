package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentRequestDTO;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.PaymentService;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // orderId 로 결제 정보 조회
    @GetMapping("/preview/{orderId}")
    public BaseResponse<PaymentResponseDTO.PaymentPreviewDTO> previewPayment(@PathVariable Long orderId, @AuthUser User user) {
        return BaseResponse.onSuccess(paymentService.previewPayment(orderId, user));
    }

    // client 에서 iamport 측으로 부터 받은 callback 정보로 검증
    @PostMapping("/validation/Payment")
    public BaseResponse<IamportResponse<Payment>> validationPayment(@RequestBody PaymentRequestDTO.CallBackDTO callBackDTO, @AuthUser User user) {
        return BaseResponse.onSuccess(paymentService.validationPayment(callBackDTO, user));
    }
}
