package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.user.UserRequestDTO;

import java.util.UUID;

public interface UserService {

    // 유저 회원 가입
    User joinUser(UserRequestDTO.JoinDTO joinDTO);

    //유저 조회
    User findUser(UUID userId);
}
