package com.example.EducationClassProject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserResponseDTO {

    // 회원가입 응답 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDTO {
        private UUID userId;
        private LocalDateTime createAt;
    }
}
