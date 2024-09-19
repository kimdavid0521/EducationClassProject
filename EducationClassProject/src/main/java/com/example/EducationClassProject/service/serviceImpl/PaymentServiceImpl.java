package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.OrderHandler;
import com.example.EducationClassProject.domain.Order;
import com.example.EducationClassProject.dto.PaymentDTO.PaymentResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.OrderRepository;
import com.example.EducationClassProject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final JWTUtil jwtUtil;
    private final OrderRepository orderRepository;

    // orderId로 결제 정보 조회
    @Override
    public PaymentResponseDTO.PaymentPreviewDTO previewPayment(Long orderId, String token) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> {
            throw new OrderHandler(ErrorStatus._NOT_FOUND_ORDER);
        });

        String orderUid = order.getOrderUid(); // 랜덤으로 지정해줬던 orderUid 를 뽑아냅니다.
        Order orderResult = orderRepository.findOrderAndPaymentAndMember(orderUid).orElseThrow(() -> {
            throw new OrderHandler(ErrorStatus._NOT_FOUND_ORDER_UID);
        });

        return PaymentResponseDTO.PaymentPreviewDTO.builder()
                .orderUid(orderResult.getOrderUid())
                .product(orderResult.getProduct())
                .paymentPrice(orderResult.getPrice())
                .buyerName(orderResult.getUser().getUsername())
                .buyerEmail(orderResult.getUser().getEmail())
                .build();

    }
}
