package com.example.EducationClassProject.converter;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.MemberStatus;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class UserConverter {

    // 회원 가입 요청 유저 converter
    public static User toUser(UserRequestDTO.JoinDTO joinDTO, PasswordEncoder passwordEncoder) {
        return User.builder()
                .username(joinDTO.getUsername())
                .gender(joinDTO.getGender())
                .email(joinDTO.getEmail())
                .phone(joinDTO.getPhone())
                .role(joinDTO.getRole())
                .password(passwordEncoder.encode(joinDTO.getPassword()))
                .point(0) // 포인트 초기값 설정
                .memberStatus(MemberStatus.ACTIVE) // 멤버 상태 초기값 설정
                .verify(Verify.FALSE) // 검증 상태 초기값 설정
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

    // 유저 전체 조회 응답 유저 list converter
    public static UserResponseDTO.FindUsersListDTO toFindUserListResultDTO(List<User> userList) {
        List<UserResponseDTO.FindUserResultDTO> usersListDTOList = userList.stream()
                .map(UserConverter::toFindUserResultDTO)
                .toList();

        return UserResponseDTO.FindUsersListDTO.builder()
                .userResultDTOList(usersListDTOList)
                .build();
    }

}
