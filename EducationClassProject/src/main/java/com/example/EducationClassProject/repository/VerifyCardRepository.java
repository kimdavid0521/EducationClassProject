package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface VerifyCardRepository extends JpaRepository<VerifyCard, Long> {

    // userId로 verify 카드가 존재하는지 확인
    boolean existsVerifyCardByUserId(UUID userId);

    // 인증이 완료된 verifyCard 조회
    @Query("SELECT v FROM VerifyCard v WHERE v.user.verify = :verify")
    List<VerifyCard> findByUserVerify(@Param("verify") Verify verify, Sort sort);


}
