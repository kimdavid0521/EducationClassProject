package com.example.EducationClassProject.repository.repositoryImpl;

import com.example.EducationClassProject.domain.QUser;
import com.example.EducationClassProject.domain.QVerifyCard;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.repository.customRepository.VerifyCardRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VerifyCardRepositoryImpl implements VerifyCardRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    // 인증이 완료된 verifyCard 조회
    @Override
    public Page<VerifyCard> findByUserVerify(Verify verify, Pageable pageable) {
        QVerifyCard verifyCard = QVerifyCard.verifyCard;

        List<VerifyCard> verifyCards = jpaQueryFactory
                .selectFrom(verifyCard)
                .where(verifyCard.user.verify.eq(verify))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .select(verifyCard.count())
                .from(verifyCard)
                .where(verifyCard.user.verify.eq(verify))
                .fetchOne();

        return new PageImpl<>(verifyCards, pageable, total);
    }
}
