package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.converter.UserConverter;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.dto.userDTO.UserResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    // 회원가입
    @Override
    @Transactional  // 데이터 변경하는 메서드라 transactional 적용
    public UserResponseDTO.JoinResultDTO joinUser(UserRequestDTO.JoinDTO joinDTO) {
        User user = UserConverter.toUser(joinDTO, passwordEncoder);
        userRepository.save(user);
        user.updateUserPoint(500); // 회원가입시 500 포인트 증정
        return new UserResponseDTO.JoinResultDTO(user.getId(), user.getCreateAt());
    }

    // 로그인
    @Override
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


    // 유저 조회
    @Override
    @Transactional(readOnly = true) // 데이터 조회이므로 성능을 위해 추가
    public UserResponseDTO.FindUserResultDTO findUser(String token) {
        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);
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

    // 유저 전체 조회
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO.FindUsersListDTO findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO.FindUserResultDTO> userResultDTOList = users.stream()
                .map(user -> UserResponseDTO.FindUserResultDTO.builder()
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
                        .build())
                .collect(Collectors.toList());

        return UserResponseDTO.FindUsersListDTO.builder()
                .userResultDTOList(userResultDTOList)
                .build();
    }

    // 유저 삭제
    @Override
    @Transactional
    public void deleteUser(String token) {
        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);
        userRepository.delete(user);
    }

    // 유저 정보 업데이트
    @Override
    @Transactional
    public UserResponseDTO.FindUserResultDTO updateUserInfo(String token, UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO) {
        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);
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
