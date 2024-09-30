package com.example.EducationClassProject.repository.customRepository;

import com.example.EducationClassProject.domain.Lecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface LectureRepositoryCustom {

    //특정 유저 id를 통해 클래스 조회하기 (단순성과 성능 최적화를 위해 매핑 엔티티에서 조회화기보단 class 엔티티에서 userId로 조회하였습니다.)
    Page<Lecture> findLecturesByUserId(UUID userId, Pageable pageable);
}
