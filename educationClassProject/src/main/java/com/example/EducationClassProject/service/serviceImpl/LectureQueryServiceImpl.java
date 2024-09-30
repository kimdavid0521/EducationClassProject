package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ClassHandler;
import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.repository.LectureRepository;
import com.example.EducationClassProject.repository.UserLectureRepository;
import com.example.EducationClassProject.service.LectureQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LectureQueryServiceImpl implements LectureQueryService {

    private final LectureRepository lectureRepository;
    private final UserLectureRepository userLectureRepository;


    // 클래스 조회 후 클래스 참여 여부 확인 ( 클래스 참여시 )
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.ResultFindClass findClass(Long classId, User user) {

        Lecture lectureEntity = lectureRepository.findById(classId).orElseThrow(() -> {
            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
        });

        // 중복 확인
        if (userLectureRepository.existsByUserAndALecture(user.getId(), lectureEntity.getId())) {
            throw new ClassHandler(ErrorStatus._ALREADY_JOINED_CLASS);
        }

        // 잔여 포인트 확인
        int currentPoint = user.getPoint();
        if (currentPoint < 100) {
            throw new ClassHandler(ErrorStatus._JOIN_POINT_NOT_ENOUGH);
        }

        return ClassResponseDTO.ResultFindClass.builder()
                .aLecture(lectureEntity)
                .user(user)
                .build();
    }

    // 유저가 참여중인 클래스 조회
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findClassesByUser(User user, Pageable pageable) {


        Page<Lecture> classPage = lectureRepository.findLecturesByUserId(user.getId(), pageable);
        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classPage.stream()
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
                .totalPages(classPage.getTotalPages())
                .totalElements(classPage.getTotalElements())
                .currentPage(classPage.getNumber())
                .pageSize(classPage.getSize())
                .build();
    }

    // 유저가 생성한 클래스 조회 ( 선생 )
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findClassesByOwner(User user, Pageable pageable) {

        if (user.getVerify().equals(Verify.FALSE)) {
            throw new ClassHandler(ErrorStatus._NOT_TEACHER);
        }
        Page<Lecture> classPage = lectureRepository.findByOwnerId(user.getId(), pageable);


        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classPage.stream()
                .map(classEntity -> ClassResponseDTO.PreviewClassResultDTO.builder()
                        .classId(classEntity.getId())
                        .className(classEntity.getClassName())
                        .classIntro(classEntity.getClassIntro())
                        .classExplain(classEntity.getClassExplain())
                        .classLevel(classEntity.getClassLevel())
                        .build())
                .collect(Collectors.toList());

        return ClassResponseDTO.PreviewClassListResultDTO.builder()
                .previewClassResultDTOList(classResultDTOList)
                .totalPages(classPage.getTotalPages())
                .totalElements(classPage.getTotalElements())
                .currentPage(classPage.getNumber())
                .pageSize(classPage.getSize())
                .build();
    }

    // 모든 클래스 조회
    @Override
    @Transactional(readOnly = true)
    public ClassResponseDTO.PreviewClassListResultDTO findAllClasses(Pageable pageable) {

        Page<Lecture> classPage = lectureRepository.findAll(pageable);
        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classPage.stream()
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
                .totalPages(classPage.getTotalPages())
                .totalElements(classPage.getTotalElements())
                .currentPage(classPage.getNumber())
                .pageSize(classPage.getSize())
                .build();

    }

    // 사용자가 owner 인지 조회 및 클래스 객체 반환
    @Override
    @Transactional(readOnly = true)
    public Lecture getOwnerClass(Long classId, User user) {

        Lecture lectureEntity = lectureRepository.findById(classId).orElseThrow(() -> {
            throw new ClassHandler(ErrorStatus._NOT_FOUND_CLASS);
        });

        if (!lectureEntity.getOwner().getId().equals(user.getId())) {
            throw new ClassHandler(ErrorStatus._NOT_CLASS_OWNER_DELETE);
        }

        return lectureEntity;
    }
}
