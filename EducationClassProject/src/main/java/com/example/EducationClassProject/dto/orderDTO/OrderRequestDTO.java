package com.example.EducationClassProject.dto.orderDTO;

import com.example.EducationClassProject.domain.enums.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderRequestDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderDTO{
        private Product product;
    }
}
