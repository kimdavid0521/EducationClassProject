package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;

import java.util.List;

public interface VerifyQueryService {

    // 유저 상태 검증 및 검증 요청 보낸적있는지 확인
    User verifyUser(String token);

    // 개인 검증서 조회
    VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(String token);

    // 인증서 요청 리스트 조회 관리자 페이지 1: 전체 조회, 2: 수락된 인증서 조회, 3: 미수락된 인증서 조회
    VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyRequestList(Integer typeNum);
}
