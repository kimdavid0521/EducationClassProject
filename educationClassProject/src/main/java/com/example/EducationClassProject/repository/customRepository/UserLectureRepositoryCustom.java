package com.example.EducationClassProject.repository.customRepository;

import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserLectureRepositoryCustom {

    //중복 참여 여부 확인
    boolean existsByUserAndALecture(@Param("userId") UUID userId, @Param("lectureId") Long lectureId);
}
