package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;

public interface UserQueryService {

    // 유저 로그인
    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO loginRequestDTO);
}
