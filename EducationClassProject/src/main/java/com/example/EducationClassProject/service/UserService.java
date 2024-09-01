package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.user.UserRequestDTO;

public interface UserService {

    User joinUser(UserRequestDTO.JoinDTO joinDTO);
}
