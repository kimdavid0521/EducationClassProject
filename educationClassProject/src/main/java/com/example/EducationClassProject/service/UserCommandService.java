package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;

public interface UserCommandService {

    // 유저 회원 가입
    UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDTO joinDTO);

    // 유저 회원 탈퇴
    void deleteUser(User user);

    // 유저 업데이트
    UserResponseDTO.FindUserResultDTO updateUserInfo(User user, UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO);
}
