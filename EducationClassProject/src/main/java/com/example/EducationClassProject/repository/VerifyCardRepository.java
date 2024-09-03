package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.VerifyCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VerifyCardRepository extends JpaRepository<VerifyCard, Long> {

    // userId로 verify 카드가 존재하는지 확인
    boolean existsVerifyCardByUserId(UUID userId);


}
