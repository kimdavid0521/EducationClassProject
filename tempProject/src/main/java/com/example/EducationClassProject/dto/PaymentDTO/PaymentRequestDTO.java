package com.example.EducationClassProject.dto.PaymentDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class PaymentRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CallBackDTO {
        private String paymentUid; // 결제 고유 번호
        private String orderUid; // 주문 고유 번호
    }
}
