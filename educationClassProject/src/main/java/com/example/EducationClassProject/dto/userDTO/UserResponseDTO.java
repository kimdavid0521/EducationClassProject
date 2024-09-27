package com.example.EducationClassProject.dto.userDTO;

import com.example.EducationClassProject.domain.enums.Gender;
import com.example.EducationClassProject.domain.enums.MemberStatus;
import com.example.EducationClassProject.domain.enums.Role;
import com.example.EducationClassProject.domain.enums.Verify;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserResponseDTO {

    // 회원가입 응답 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JoinResultDTO {
        private UUID userId;
        private LocalDateTime createAt;

    }

    // 로그인 응답 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class LoginResultDTO {
        private UUID userId;
        private String token;
    }


    // 유저 조회 응답 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindUserResultDTO {
        private UUID userId;
        private String username;
        private Gender gender;
        private String email;
        private String phone;
        private Integer point;
        private MemberStatus memberState;
        private Role role;
        private Verify verify;
        private LocalDateTime createAt;
    }

    // 유저 전체 조회(list)
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class FindUsersListDTO {
        List<FindUserResultDTO> userResultDTOList;
        int totalPages;
        long totalElements;
        int currentPage;
        int pageSize;
    }
}
