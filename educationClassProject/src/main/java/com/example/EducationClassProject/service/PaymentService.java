package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentRequestDTO;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

public interface PaymentService {

    // orderId 로 결제 정보 조회
    PaymentResponseDTO.PaymentPreviewDTO previewPayment(Long orderId, User user);

    // callback 정보로 결제 인증
    IamportResponse<Payment> validationPayment(PaymentRequestDTO.CallBackDTO callBackDTO, User user);
}
