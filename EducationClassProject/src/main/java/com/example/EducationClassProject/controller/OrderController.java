package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.dto.orderDTO.OrderRequestDTO;
import com.example.EducationClassProject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 번호 생성 api
    @PostMapping("/order/request")
    public BaseResponse<Long> createOrder(@RequestBody OrderRequestDTO.CreateOrderDTO createOrderDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(orderService.createOrder(createOrderDTO,token));
    }



}
