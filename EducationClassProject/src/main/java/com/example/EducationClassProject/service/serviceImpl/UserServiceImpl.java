package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.user.UserRequestDTO;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional  // 데이터 변경하는 메서드라 transactional 적용
    public User joinUser(UserRequestDTO.JoinDTO joinDTO) {
        return null;
    }
}
