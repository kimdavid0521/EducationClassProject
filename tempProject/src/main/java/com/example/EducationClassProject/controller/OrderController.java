package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.orderDTO.OrderRequestDTO;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 번호 생성 api
    // 현재 등록된 상품: COUPON_1000, COUPON_2000, COUPON_3000, COUPON_4000
    @PostMapping("/order/request")
    public BaseResponse<Long> createOrder(@RequestBody OrderRequestDTO.CreateOrderDTO createOrderDTO, @AuthUser User user) {
        return BaseResponse.onSuccess(orderService.createOrder(createOrderDTO,user));
    }
}
