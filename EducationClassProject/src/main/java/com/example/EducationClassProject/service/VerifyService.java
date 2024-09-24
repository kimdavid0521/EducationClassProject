package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;

import java.util.List;
import java.util.UUID;

public interface VerifyService {

    // 유저 선생 검증 요청 보내기
//    VerifyCard applyVerify(String token, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO);

    // 개인 검증서 조회
//    VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(String token);

    // 검증서 전체보기 ( 관리자 페이지 )
//    VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyCardList(Integer typeNum);

    // 검증서 수락하기 ( 관리자 페이지 )
    VerifyResponseDTO.PreviewVerifyCardDTO acceptVerifyCard(Long verifyCardId);

}
