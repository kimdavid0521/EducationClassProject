package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserClass;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.repository.ClassRepository;
import com.example.EducationClassProject.repository.UserClassRepository;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final UserClassRepository userClassRepository;

    @Override
    @Transactional
    public Class createClass(ClassRequestDTO.CreateClassDTO createClassDTO, UUID userId) {
        Class classEntity = Class.builder()
                .className(createClassDTO.getClassName())
                .classIntro(createClassDTO.getClassIntro())
                .classExplain(createClassDTO.getClassExplain())
                .classLevel(createClassDTO.getClassLevel())
                .classDay(createClassDTO.getClassDay())
                .classStartDay(createClassDTO.getClassStartDay())
                .classTest(createClassDTO.getClassTest())
                .build();
        classRepository.save(classEntity);

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });

        UserClass userClass = UserClass.builder()
                .user(user)
                .aClass(classEntity)
                .build();

        userClassRepository.save(userClass);

        return classEntity;
    }

    // 클래스 객체 유저 id로 조회화기
    @Override
    @Transactional(readOnly = true)
    public List<Class> findClassesByUserId(UUID userId) {
        return classRepository.findClassesByUserId(userId);
    }

    // 클래스 전체 조회
    @Override
    @Transactional(readOnly = true)
    public List<Class> findAllClasses() {
        return classRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 클래스 생성된 날짜 순으로 가져오기
    }
}
