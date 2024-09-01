package com.example.EducationClassProject.converter;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.user.UserRequestDTO;
import com.example.EducationClassProject.dto.user.UserResponseDTO;

public class UserConverter {

    // 회원 가입 요청 유저 converter
    public static User toUser(UserRequestDTO.JoinDTO joinDTO) {
        return User.builder()
                .username(joinDTO.getUsername())
                .gender(joinDTO.getGender())
                .email(joinDTO.getEmail())
                .phone(joinDTO.getPhone())
                .role(joinDTO.getRole())
                .build();
    }

    // 회원 가입 응답 유저 converter
    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createAt(user.getCreateAt())
                .build();
    }

    // 유저 조회 응답 유저 converter
    public static UserResponseDTO.FindUserResultDTO toFindUserResultDTO(User user) {
        return UserResponseDTO.FindUserResultDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .gender(user.getGender())
                .email(user.getEmail())
                .phone(user.getPhone())
                .point(user.getPoint())
                .memberState(user.getMemberStatus())
                .role(user.getRole())
                .verify(user.getVerify())
                .createAt(user.getCreateAt())
                .build();
    }

}
