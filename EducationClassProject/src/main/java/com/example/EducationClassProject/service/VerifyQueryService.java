package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;

public interface VerifyQueryService {

    // 유저 상태 검증 및 검증 요청 보낸적있는지 확인
    User verifyUser(String token);

    // 개인 검증서 조회
    VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(String token);
}
