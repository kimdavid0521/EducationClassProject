package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.mapping.UserLecture;
import com.example.EducationClassProject.repository.customRepository.UserLectureRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long>, UserLectureRepositoryCustom {
    // 중복 참여 여부 확인
//    @Query("SELECT CASE WHEN COUNT(uc) > 0 THEN true ELSE false END " +
//            "FROM UserLecture uc WHERE uc.user.id = :userId AND uc.aLecture.id = :classId")
//    boolean existsByUserAndALecture(@Param("userId") UUID userId, @Param("classId") Long classId);

}
