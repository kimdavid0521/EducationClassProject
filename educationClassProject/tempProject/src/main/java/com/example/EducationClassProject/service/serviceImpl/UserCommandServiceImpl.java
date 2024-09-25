package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.converter.UserConverter;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;


    // 유저 회원가입
    @Override
    @Transactional
    public UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDTO joinDTO) {
        User user = UserConverter.toUser(joinDTO, passwordEncoder);
        userRepository.save(user);
        user.updateUserPoint(500); // 회원가입시 500 포인트 증정
        return new UserResponseDTO.JoinResultDTO(user.getId(), user.getCreateAt());
    }

    // 유저 회원 탈퇴
    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // 유저 개인정보 수정
    @Override
    @Transactional
    public UserResponseDTO.FindUserResultDTO updateUserInfo(User user, UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO) {

        user.updateUser(updateUserInfoDTO);  // 유저 엔티티에 작성해준 업데이트 함수 사용하여서 데이터 업데이트 (더티 체킹을 통한 업데이트 구현)
        return new UserResponseDTO.FindUserResultDTO(user.getId(),
                user.getUsername(),
                user.getGender(),
                user.getEmail(),
                user.getPhone(),
                user.getPoint(),
                user.getMemberStatus(),
                user.getRole(),
                user.getVerify(),
                user.getCreateAt());
    }
}
