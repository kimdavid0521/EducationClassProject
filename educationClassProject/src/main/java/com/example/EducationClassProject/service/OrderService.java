package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.orderDTO.OrderRequestDTO;

public interface OrderService {

    Long createOrder(OrderRequestDTO.CreateOrderDTO createOrderDTO, User user);
}
