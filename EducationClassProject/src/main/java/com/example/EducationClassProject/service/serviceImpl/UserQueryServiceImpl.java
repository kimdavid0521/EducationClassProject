package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    // 유저 로그인
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO.LoginResultDTO loginUser(UserRequestDTO.LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_EMAIL);
        });

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new UserHandler(ErrorStatus._PASSWORD_NOT_OK);
        }

        String token = jwtUtil.createAccessToken(user.getEmail(), user.getRole().toString());

        return new UserResponseDTO.LoginResultDTO(user.getId(),token);
    }
}
