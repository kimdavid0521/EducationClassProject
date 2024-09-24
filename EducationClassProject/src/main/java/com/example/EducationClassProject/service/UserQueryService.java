package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;

public interface UserQueryService {

    // 유저 로그인
    UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO loginRequestDTO);

    // 개인 유저 조회
    UserResponseDTO.FindUserResultDTO findUser(User user);

    // 유저 전체 조회
    UserResponseDTO.FindUsersListDTO findAllUsers();
}
