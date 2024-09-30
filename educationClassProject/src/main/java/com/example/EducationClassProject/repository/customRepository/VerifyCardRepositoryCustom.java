package com.example.EducationClassProject.repository.customRepository;

import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface VerifyCardRepositoryCustom {

    // 인증이 완료된 verifyCard 조회
    Page<VerifyCard> findByUserVerify(@Param("verify") Verify verify, Pageable pageable);
}
