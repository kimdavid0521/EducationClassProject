package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;

import java.util.List;
import java.util.UUID;

public interface VerifyService {

    // 유저 선생 검증 요청 보내기
    VerifyCard applyVerify(UUID userId, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO);

    // 개인 검증서 조회
    VerifyCard previewVerifyCard(UUID userId);

    // 검증서 전체보기 ( 관리자 페이지 )
    List<VerifyCard> previewVerifyCardList(Integer typeNum);

    // 검증서 수락하기 ( 관리자 페이지 )
    VerifyCard acceptVerifyCard(Long verifyCardId);

}
