package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.converter.UserConverter;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.userDTO.UserRequestDTO;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional  // 데이터 변경하는 메서드라 transactional 적용
    public User joinUser(UserRequestDTO.JoinDTO joinDTO) {
        User user = UserConverter.toUser(joinDTO, passwordEncoder);
        return userRepository.save(user);
    }

    // 유저 조회
    @Override
    @Transactional(readOnly = true) // 데이터 조회이므로 성능을 위해 추가
    public User findUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER); // 유저 uuid로 조회 안될 시 유저 에러핸들러로 에러처리
        });
        return user;
    }

    // 유저 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // 유저 삭제
    @Override
    @Transactional
    public void deleteUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });

        userRepository.delete(user);
    }

    // 유저 정보 업데이트
    @Override
    @Transactional
    public User updateUserInfo(UUID userId, UserRequestDTO.UpdateUserInfoDTO updateUserInfoDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });

        user.updateUser(updateUserInfoDTO);  // 유저 엔티티에 작성해준 업데이트 함수 사용하여서 데이터 업데이트 (더티 체킹을 통한 업데이트 구현)
        return user;
    }


}
