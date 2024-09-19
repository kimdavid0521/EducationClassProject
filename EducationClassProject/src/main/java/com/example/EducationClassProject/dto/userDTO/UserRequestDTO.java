package com.example.EducationClassProject.dto.userDTO;

import com.example.EducationClassProject.domain.enums.Gender;
import com.example.EducationClassProject.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserRequestDTO {

    //user 로그인 dto
    @Getter
    public static class LoginRequestDTO {
        private String email;
        private String password;
    }

    // user 회원가입 dto
    @Getter
    public static class JoinDTO {

        private String username;
        private Gender gender;
        private String email;
        private String phone;
        private Role role;
        private String password;
    }

    // user 개인정보 수정 dto
    @Getter
    public static class UpdateUserInfoDTO {

        private String username;
        private Gender gender;
        private String email;
        private String phone;
        private Role role;
    }
}
