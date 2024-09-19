package com.example.EducationClassProject.dto.PaymentDTO;

import com.example.EducationClassProject.domain.enums.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class PaymentResponseDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentPreviewDTO {
        private String orderUid;
        private Product product;
        private BigDecimal paymentPrice;
        private String buyerName;
        private String buyerEmail;

    }
}
