package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.repository.VerifyCardRepository;
import com.example.EducationClassProject.service.VerifyCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VerifyCommandServiceImpl implements VerifyCommandService {

    private final VerifyCardRepository verifyCardRepository;

    // 검증 카드 요청 보내기
    @Override
    @Transactional
    public VerifyCard requestVerify(User user, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO) {
        VerifyCard verifyCard = VerifyCard.builder()
                .info(applyVerifyDTO.getInfo())
                .grade(applyVerifyDTO.getGrade())
                .career(applyVerifyDTO.getCareer())
                .link(applyVerifyDTO.getLink())
                .user(user)
                .build();

        verifyCardRepository.save(verifyCard);

        return verifyCard;
    }

    // 유저 검증서 수락하기 ( 관리자 페이지 )
    @Override
    @Transactional
    public void acceptUserVerifyState(User user) {
        user.updateVerify(); // 유저 검증 상태 업데이트
    }
}
