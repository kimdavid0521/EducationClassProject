package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.OrderHandler;
import com.example.EducationClassProject.domain.Order;
import com.example.EducationClassProject.domain.Payment;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.PaymentState;
import com.example.EducationClassProject.domain.enums.Product;
import com.example.EducationClassProject.dto.orderDTO.OrderRequestDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.OrderRepository;
import com.example.EducationClassProject.repository.PaymentRepository;
import com.example.EducationClassProject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final JWTUtil jwtUtil;
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    // 주문 번호 생성
    @Override
    @Transactional
    public Long createOrder(OrderRequestDTO.CreateOrderDTO createOrderDTO, User user) {

        Product product = null;
        BigDecimal price = BigDecimal.valueOf(0);

        if (createOrderDTO.getProduct().equals(Product.COUPON_1000)) {
            product = Product.COUPON_1000;
            price = BigDecimal.valueOf(1000);
        } else if (createOrderDTO.getProduct().equals(Product.COUPON_2000)) {
            product = Product.COUPON_2000;
            price = BigDecimal.valueOf(2000);
        } else if (createOrderDTO.getProduct().equals(Product.COUPON_3000)) {
            product = Product.COUPON_3000;
            price = BigDecimal.valueOf(3000);
        } else if (createOrderDTO.getProduct().equals(Product.COUPON_4000)) {
            product = Product.COUPON_4000;
            price = BigDecimal.valueOf(4000);
        } else {
            throw new OrderHandler(ErrorStatus._NOT_FOUND_PRODUCT);
        }

        Payment payment = Payment.builder()
                .price(price)
                .paymentState(PaymentState.ING)
                .build();

        paymentRepository.save(payment);

        Order order = Order.builder()
                .user(user)
                .price(price)
                .product(product)
                .orderUid(UUID.randomUUID().toString())
                .payment(payment)
                .build();

        orderRepository.save(order);

        return order.getId();
    }
}
