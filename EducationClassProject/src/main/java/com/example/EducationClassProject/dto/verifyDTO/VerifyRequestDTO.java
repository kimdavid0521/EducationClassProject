package com.example.EducationClassProject.dto.verifyDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VerifyRequestDTO {

    // 인증서 발급 요청 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ApplyVerifyDTO {
        private String info;
        private String grade;
        private String career;
        private String link;
    }

}
