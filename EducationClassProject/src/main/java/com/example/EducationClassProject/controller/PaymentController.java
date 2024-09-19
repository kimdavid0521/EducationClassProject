package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;
import com.example.EducationClassProject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // orderId 로 결제 정보 조회
    @GetMapping("/preview/{orderId}")
    public BaseResponse<PaymentResponseDTO.PaymentPreviewDTO> previewPayment(@PathVariable Long orderId, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(paymentService.previewPayment(orderId, token));
    }

//    @PostMapping("/validation/Payment")
//    public BaseResponse<>
}
