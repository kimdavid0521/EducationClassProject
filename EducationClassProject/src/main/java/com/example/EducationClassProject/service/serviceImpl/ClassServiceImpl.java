package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ClassHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserClass;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

    private final ClassRepository classRepository;
    private final UserRepository userRepository;
    private final UserClassRepository userClassRepository;
    private final JWTUtil jwtUtil;

    @Override
    @Transactional
    public ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, String token) {
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

        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);

        UserClass userClass = UserClass.builder()
                .user(user)
                .aClass(classEntity)
                .build();

        userClassRepository.save(userClass);

        return new ClassResponseDTO.CreateClassResultDTO(classEntity.getId(), classEntity.getClassName(), classEntity.getClassIntro());
    }

    // 클래스 객체 유저 id로 조회화기
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findClassesByUserId(String token) {
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

    // 클래스 전체 조회
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findAllClasses() {
        List<Class> classes = classRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 클래스 생성된 날짜 순으로 가져오기
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

    // 클래스 삭제
    @Override
    @Transactional
    public void deleteClass(Long classId) {
        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
        });

        classRepository.delete(classEntity);
    }

    // 클래스 업데이트
    @Override
    @Transactional
    public ClassResponseDTO.PreviewClassResultDTO updateClass(Long classId, ClassRequestDTO.UpdateClassDTO updateClassDTO) {
        Class classEntity = classRepository.findById(classId).orElseThrow(() -> {
            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
        });

        classEntity.updateClass(updateClassDTO); // 더티 체킹을 통한 업데이트
        return new ClassResponseDTO.PreviewClassResultDTO(classEntity.getId(), classEntity.getClassName(), classEntity.getClassIntro(), classEntity.getClassExplain(), classEntity.getClassLevel());
    }
}
