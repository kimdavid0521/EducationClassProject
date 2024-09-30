package com.example.EducationClassProject.repository.customRepository;

import com.example.EducationClassProject.domain.Order;

import java.util.Optional;

public interface OrderRepositoryCustom {
    Optional<Order> findOrderAndPaymentAndMember(String orderUid);

    Optional<Order> findOrderAndPayment(String orderUid);
}
