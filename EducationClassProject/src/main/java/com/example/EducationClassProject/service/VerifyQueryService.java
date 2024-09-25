package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;

public interface VerifyQueryService {

    // 유저 상태 검증 및 검증 요청 보낸적있는지 확인
    User verifyUser(User user);

    // 개인 검증서 조회
    VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(User user);

    // 인증서 요청 리스트 조회 ( 관리자 페이지 1: 전체 조회, 2: 수락된 인증서 조회, 3: 미수락된 인증서 조회 )
    VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyRequestList(Integer typeNum);

    // 유저 검증 카드 조회 및 해당 유저 반환( 관리자 페이지에서 조회 후 상태 업데이트 해여하기에 cardId로 받았습니다. )
    User getVerifyCardUserForUpdate(Long verifyCardId);
}
