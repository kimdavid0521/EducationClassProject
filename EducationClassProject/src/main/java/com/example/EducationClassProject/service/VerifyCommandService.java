package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;

public interface VerifyCommandService {

    // 검증 카드 요청 보내기
    VerifyCard requestVerify(User user, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO);
}
