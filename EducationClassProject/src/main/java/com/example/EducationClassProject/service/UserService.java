package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {

    // 유저 회원 가입
    User joinUser(UserRequestDTO.JoinDTO joinDTO);

    // 유저 조회
    User findUser(UUID userId);

    // 유저 전체 조회
    List<User> findAllUsers();

    // 유저 삭제
    void deleteUser(UUID userId);

    // 유저 업데이트
    User updateUserInfo(UUID userId, UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO);
}
