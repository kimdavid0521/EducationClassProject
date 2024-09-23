package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;

public interface UserCommandService {

    // 유저 회원 가입
    UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDTO joinDTO);
}
