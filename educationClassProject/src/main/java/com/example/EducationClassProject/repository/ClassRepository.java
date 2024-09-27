package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Class;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    //특정 유저 id를 통해 클래스 조회하기 (단순성과 성능 최적화를 위해 매핑 엔티티에서 조회화기보단 class 엔티티에서 userId로 조회하였습니다.)
    @Query("SELECT uc.aClass FROM UserClass uc WHERE uc.user.id = :userId")
    List<Class> findClassesByUserId(UUID userId);

    // 자신이 생성한 강의만 조회
    List<Class> findByOwnerId(UUID ownerId);

    // 강의 목록 전체 조회 페이지 네이션
    Page<Class> findAllPage(Pageable pageable);
}
