package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.VerifyHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.repository.VerifyCardRepository;
import com.example.EducationClassProject.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VerifyServiceImpl implements VerifyService {

    private final UserRepository userRepository;
    private final VerifyCardRepository verifyCardRepository;

    // 유저 선생 검증 요청
    @Override
    @Transactional
    public VerifyCard applyVerify(UUID userId, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO) {

        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });

        // 유저의 검증 상태 확인
        if (user.getVerify() == Verify.TRUE) {
            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
        }

        // verify 요청을 보낸적이 있는지 검증
        boolean hasVerifyRequest = verifyCardRepository.existsVerifyCardByUserId(userId);
        if (hasVerifyRequest) {
            throw new UserHandler(ErrorStatus._ALREADY_REQUEST_VERIFY);
        }

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

    // 개인 검증서 조회
    @Override
    @Transactional(readOnly = true)
    public VerifyCard previewVerifyCard(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserHandler(ErrorStatus._NOT_FOUND_USER);
        });

        VerifyCard verifyCard = user.getVerifyCard();
        if (verifyCard == null) {
            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
        }

        return verifyCard;
    }

    // 검증 요청 내역 조회 ( 관리자 페이지 )
    @Override
    @Transactional(readOnly = true)
    public List<VerifyCard> previewVerifyCardList(Integer typeNum) {
        if (typeNum.equals(1)) {
            List<VerifyCard> verifyCardList = verifyCardRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 검증서 인증 요청 시간순으로 정렬
            return verifyCardList;
        } else if (typeNum.equals(2)) {
            List<VerifyCard> verifyCardList = verifyCardRepository.findByUserVerify(Verify.TRUE, Sort.by(Sort.Direction.DESC, "createAt"));
            return verifyCardList;
        } else if (typeNum.equals(3)) {
            List<VerifyCard> verifyCardList = verifyCardRepository.findByUserVerify(Verify.FALSE, Sort.by(Sort.Direction.DESC, "createAt"));
            return verifyCardList;
        } else {
            throw new VerifyHandler(ErrorStatus._BAD_REQUEST);
        }
    }

    // 유저 검증 상태 업데이트
    @Override
    @Transactional
    public VerifyCard acceptVerifyCard(Long verifyCardId) {
        VerifyCard verifyCard = verifyCardRepository.findById(verifyCardId).orElseThrow(() -> {
            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
        });

        User user = verifyCard.getUser();

        if (user.getVerify() == Verify.TRUE) {
            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
        }

        user.updateVerify(); // 유저 검증 상태 업데이트

        return verifyCard;
    }
}
