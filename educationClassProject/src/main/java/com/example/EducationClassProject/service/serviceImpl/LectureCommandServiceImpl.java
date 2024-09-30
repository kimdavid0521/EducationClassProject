package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ClassHandler;
import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.domain.mapping.UserLecture;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.repository.LectureRepository;
import com.example.EducationClassProject.repository.UserLectureRepository;
import com.example.EducationClassProject.service.LectureCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LectureCommandServiceImpl implements LectureCommandService {

    private final LectureRepository lectureRepository;
    private final UserLectureRepository userLectureRepository;

    // 클래스 생성
    @Override
    @Transactional
    public ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, User user) {

        if (user.getVerify().equals(Verify.FALSE)) {
            throw new ClassHandler(ErrorStatus._NOT_VERIFY_CLASS);
        }

        // 잔여 포인트 검증
        int currentPoint =  user.getPoint();
        if (currentPoint < 200) {
            throw new ClassHandler(ErrorStatus._MAKE_POINT_NOT_ENOUGH);
        }

        Lecture lectureEntity = Lecture.builder()
                .className(createClassDTO.getClassName())
                .classIntro(createClassDTO.getClassIntro())
                .classExplain(createClassDTO.getClassExplain())
                .classLevel(createClassDTO.getClassLevel())
                .classDay(createClassDTO.getClassDay())
                .classStartDay(createClassDTO.getClassStartDay())
                .classTest(createClassDTO.getClassTest())
                .owner(user)
                .build();

        lectureRepository.save(lectureEntity);

        // 포인트 차감
        user.updateUserPoint(currentPoint - 200);

        return new ClassResponseDTO.CreateClassResultDTO(lectureEntity.getId(), lectureEntity.getClassName(), lectureEntity.getClassIntro());
    }

    // 강의 참여
    @Override
    @Transactional
    public Long joinClass(ClassResponseDTO.ResultFindClass resultFindClass) {

        UserLecture userLecture = UserLecture.builder()
                .user(resultFindClass.getUser())
                .aLecture(resultFindClass.getALecture())
                .build();
        userLectureRepository.save(userLecture);

        int currentPoint = resultFindClass.getUser().getPoint();

        // 포인트 차감
        resultFindClass.getUser().updateUserPoint(currentPoint - 100);
        return resultFindClass.getALecture().getId();
    }

    // 클래스 삭제
    @Override
    @Transactional
    public void deleteClass(Lecture aLecture) {
        lectureRepository.delete(aLecture);
    }

    // 클래스 정보 업데이트
    @Override
    @Transactional
    public ClassResponseDTO.PreviewClassResultDTO updateClass(Lecture aLecture, ClassRequestDTO.UpdateClassDTO updateClassDTO) {
        aLecture.updateClass(updateClassDTO); // 더티 체킹을 통한 업데이트
        return new ClassResponseDTO.PreviewClassResultDTO(aLecture.getId(), aLecture.getClassName(), aLecture.getClassIntro(), aLecture.getClassExplain(), aLecture.getClassLevel());
    }
}
