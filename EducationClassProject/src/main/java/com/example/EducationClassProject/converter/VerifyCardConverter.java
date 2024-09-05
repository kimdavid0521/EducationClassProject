package com.example.EducationClassProject.converter;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;

import java.util.List;
import java.util.UUID;

public class VerifyCardConverter {

    // 검증서 조회
    public static VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCardDTO(VerifyCard verifyCard) {
        return VerifyResponseDTO.PreviewVerifyCardDTO.builder()
                .info(verifyCard.getInfo())
                .grade(verifyCard.getGrade())
                .career(verifyCard.getCareer())
                .link(verifyCard.getLink())
                .userId(verifyCard.getUser().getId())
                .username(verifyCard.getUser().getUsername())
                .isVerify(verifyCard.getUser().getVerify())
                .build();
    }

    // 검증서 조회 리스트
    public static VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyCardList(List<VerifyCard> verifyCardList) {
        List<VerifyResponseDTO.PreviewVerifyCardDTO> previewVerifyCardDTOList = verifyCardList.stream()
                .map(VerifyCardConverter::previewVerifyCardDTO)
                .toList();

        return VerifyResponseDTO.PreviewVerifyCardListDTO.builder()
                .previewVerifyCardDTOList(previewVerifyCardDTOList)
                .build();
    }
}
