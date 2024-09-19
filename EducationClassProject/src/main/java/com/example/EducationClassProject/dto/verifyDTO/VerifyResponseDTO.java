package com.example.EducationClassProject.dto.verifyDTO;

import com.example.EducationClassProject.domain.enums.Verify;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

public class VerifyResponseDTO {

    // 검증서 발급 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewVerifyCardDTO {

        private String username;
        private Verify isVerify;
        private Long verifyCardId;
        private String info;
        private String grade;
        private String career;
        private String link;

    }

    // 검증 신청 내역 조회
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewVerifyCardListDTO {
       List<PreviewVerifyCardDTO> previewVerifyCardDTOList;
    }
}
