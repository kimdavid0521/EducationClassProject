package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;

public interface PaymentService {

    // orderId 로 결제 정보 조회
    PaymentResponseDTO.PaymentPreviewDTO previewPayment(Long orderId, String token);
}
