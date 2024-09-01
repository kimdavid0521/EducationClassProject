package com.example.EducationClassProject.dto.user;

import com.example.EducationClassProject.domain.enums.Gender;
import com.example.EducationClassProject.domain.enums.Role;
import lombok.Getter;


public class UserRequestDTO {

    // user 회원가입 dto
    @Getter
    public static class JoinDTO {

        private String username;
        private Gender gender;
        private String email;
        private String phone;
        private Role role;
    }
}
