package com.example.EducationClassProject.repository.repositoryImpl;

import com.example.EducationClassProject.domain.mapping.QUserLecture;
import com.example.EducationClassProject.repository.customRepository.UserLectureRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserLectureRepositoryImpl implements UserLectureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // 유저의 강의 중복참여 확인
    @Override
    public boolean existsByUserAndALecture(UUID userId, Long lectureId) {
        QUserLecture userLecture = QUserLecture.userLecture;

        long count = jpaQueryFactory
                .select(userLecture.count())
                .from(userLecture)
                .where(userLecture.user.id.eq(userId)
                        .and(userLecture.aLecture.id.eq(lectureId)))
                .fetchOne();

        return count > 0;
    }
}
