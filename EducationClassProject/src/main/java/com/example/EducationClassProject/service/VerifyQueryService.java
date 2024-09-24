package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;

public interface VerifyQueryService {

    // 유저 상태 검증 및 검증 요청 보낸적있는지 확인
    User verifyUser(String token);
}
