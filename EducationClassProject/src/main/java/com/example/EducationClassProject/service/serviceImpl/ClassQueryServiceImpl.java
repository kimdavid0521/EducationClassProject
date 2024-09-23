package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ClassHandler;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ClassRepository;
import com.example.EducationClassProject.repository.UserClassRepository;
import com.example.EducationClassProject.service.ClassQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassQueryServiceImpl implements ClassQueryService {

    private final JWTUtil jwtUtil;
    private final ClassRepository classRepository;
    private final UserClassRepository userClassRepository;


    // 클래스 조회 후 클래스 참여 여부 확인 ( 클래스 참여시 )
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.ResultFindClass findClass(Long classId, String token) {

        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
        });

        // 중복 확인
        if (userClassRepository.existsByUserAndAClass(user.getId(), classEntity.getId())) {
            throw new ClassHandler(ErrorStatus._ALREADY_JOINED_CLASS);
        }

        // 잔여 포인트 확인
        int currentPoint = user.getPoint();
        if (currentPoint < 100) {
            throw new ClassHandler(ErrorStatus._JOIN_POINT_NOT_ENOUGH);
        }

        return ClassResponseDTO.ResultFindClass.builder()
                .aClass(classEntity)
                .user(user)
                .build();
    }

    // 유저가 참여중인 클래스 조회
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findClassesByUser(String token) {

        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);

        List<Class> classes = classRepository.findClassesByUserId(user.getId());
        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classes.stream()
                .map(clas -> ClassResponseDTO.PreviewClassResultDTO.builder()
                        .classId(clas.getId())
                        .className(clas.getClassName())
                        .classIntro(clas.getClassIntro())
                        .classExplain(clas.getClassExplain())
                        .classLevel(clas.getClassLevel())
                        .build())
                .collect(Collectors.toList());

        return ClassResponseDTO.PreviewClassListResultDTO.builder()
                .previewClassResultDTOList(classResultDTOList)
                .build();
    }
}
