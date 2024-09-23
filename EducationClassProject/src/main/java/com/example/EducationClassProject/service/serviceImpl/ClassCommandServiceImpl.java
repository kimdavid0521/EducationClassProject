package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ClassHandler;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ClassRepository;
import com.example.EducationClassProject.service.ClassCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClassCommandServiceImpl implements ClassCommandService {

    private final JWTUtil jwtUtil;
    private final ClassRepository classRepository;

    // 클래스 생성
    @Override
    @Transactional
    public ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, String token) {
        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);
        if (user.getVerify().equals(Verify.FALSE)) {
            throw new ClassHandler(ErrorStatus._NOT_VERIFY_CLASS);
        }

        // 잔여 포인트 검증
        int currentPoint =  user.getPoint();
        if (currentPoint < 200) {
            throw new ClassHandler(ErrorStatus._MAKE_POINT_NOT_ENOUGH);
        }

        Class classEntity = Class.builder()
                .className(createClassDTO.getClassName())
                .classIntro(createClassDTO.getClassIntro())
                .classExplain(createClassDTO.getClassExplain())
                .classLevel(createClassDTO.getClassLevel())
                .classDay(createClassDTO.getClassDay())
                .classStartDay(createClassDTO.getClassStartDay())
                .classTest(createClassDTO.getClassTest())
                .owner(user)
                .build();

        classRepository.save(classEntity);

        // 포인트 차감
        user.updateUserPoint(currentPoint - 200);

        return new ClassResponseDTO.CreateClassResultDTO(classEntity.getId(), classEntity.getClassName(), classEntity.getClassIntro());
    }
}
