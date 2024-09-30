package com.example.EducationClassProject.repository.repositoryImpl;

import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.domain.QLecture;
import com.example.EducationClassProject.domain.mapping.QUserLecture;
import com.example.EducationClassProject.repository.customRepository.LectureRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    //특정 유저 id를 통해 클래스 조회하기 (단순성과 성능 최적화를 위해 매핑 엔티티에서 조회화기보단 class 엔티티에서 userId로 조회하였습니다.)
    //querydsl 로 리팩토링
    @Override
    public Page<Lecture> findLecturesByUserId(UUID userId, Pageable pageable) {
        QUserLecture userLecture = QUserLecture.userLecture;
        QLecture lecture = QLecture.lecture;

        List<Lecture> lectures = jpaQueryFactory
                .select(userLecture.aLecture)
                .from(userLecture)
                .leftJoin(userLecture.aLecture, lecture)
                .where(userLecture.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .select(userLecture.count())
                .from(userLecture)
                .where(userLecture.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(lectures, pageable, total);
    }
}
