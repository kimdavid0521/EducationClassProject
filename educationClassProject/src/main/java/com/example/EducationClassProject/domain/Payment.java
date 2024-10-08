package com.example.EducationClassProject.domain;

import com.example.EducationClassProject.domain.enums.PaymentState;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private PaymentState paymentState;

    // 주문 고유 번호
    private String paymentUid;

    // 결제 성공시 업데이트 편의 메서드
    public void updatePaymentState(PaymentState paymentState, String paymentUid) {
        this.paymentState = paymentState;
        this.paymentUid = paymentUid;
    }
}
