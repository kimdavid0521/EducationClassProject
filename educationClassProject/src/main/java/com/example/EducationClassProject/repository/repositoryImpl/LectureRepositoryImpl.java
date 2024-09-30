package com.example.EducationClassProject.repository.repositoryImpl;

import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.repository.customRepository.LectureRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Lecture> findLecturesByUserId(UUID userId, Pageable pageable) {
        return null;
    }
}
