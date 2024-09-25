package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.VerifyHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.VerifyCardRepository;
import com.example.EducationClassProject.service.VerifyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerifyQueryServiceImpl implements VerifyQueryService {

    private final VerifyCardRepository verifyCardRepository;
    private final JWTUtil jwtUtil;

    // 유저 상태 검증 및 검증 요청 보낸적 있는지 확인
    @Override
    @Transactional(readOnly = true)
    public User verifyUser(User user) {

        // 유저의 검증 상태 확인
        if (user.getVerify() == Verify.TRUE) {
            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
        }

        // verify 요청을 보낸적이 있는지 검증
        boolean hasVerifyRequest = verifyCardRepository.existsVerifyCardByUserId(user.getId());
        if (hasVerifyRequest) {
            throw new UserHandler(ErrorStatus._ALREADY_REQUEST_VERIFY);
        }

        return user;
    }

    // 개인 검증서 조회
    @Override
    @Transactional(readOnly = true)
    public VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(User user) {

        VerifyCard verifyCard = user.getVerifyCard();
        if (verifyCard == null) {
            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
        }

        return new VerifyResponseDTO.PreviewVerifyCardDTO(verifyCard.getUser().getUsername(),
                verifyCard.getUser().getVerify(),
                verifyCard.getId(),
                verifyCard.getInfo(),
                verifyCard.getGrade(),
                verifyCard.getCareer(),
                verifyCard.getLink());
    }

    // 인증서 요청 리스트 조회 관리자 페이지 1: 전체 조회, 2: 수락된 인증서 조회, 3: 미수락된 인증서 조회
    @Override
    @Transactional(readOnly = true)
    public VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyRequestList(Integer typeNum) {

        List<VerifyCard> verifyCardList;
        if (typeNum.equals(1)) {
            verifyCardList = verifyCardRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 검증서 인증 요청 시간순으로 정렬
        } else if (typeNum.equals(2)) {
            verifyCardList = verifyCardRepository.findByUserVerify(Verify.TRUE, Sort.by(Sort.Direction.DESC, "createAt"));
        } else if (typeNum.equals(3)) {
            verifyCardList = verifyCardRepository.findByUserVerify(Verify.FALSE, Sort.by(Sort.Direction.DESC, "createAt"));
        } else {
            throw new VerifyHandler(ErrorStatus._BAD_REQUEST);
        }

        List<VerifyResponseDTO.PreviewVerifyCardDTO> verifyCardDTOS = verifyCardList.stream()
                .map(card -> VerifyResponseDTO.PreviewVerifyCardDTO.builder()
                        .username(card.getUser().getUsername())
                        .isVerify(card.getUser().getVerify())
                        .verifyCardId(card.getId())
                        .info(card.getInfo())
                        .grade(card.getGrade())
                        .career(card.getCareer())
                        .link(card.getLink())
                        .build())
                .collect(Collectors.toList());

        return VerifyResponseDTO.PreviewVerifyCardListDTO.builder()
                .previewVerifyCardDTOList(verifyCardDTOS)
                .build();
    }

    // 유저 검증 카드 조회 및 해당 유저 반환( 관리자 페이지에서 조회 후 상태 업데이트 해여하기에 cardId로 받았습니다. )
    @Override
    @Transactional(readOnly = true)
    public User getVerifyCardUserForUpdate(Long verifyCardId) {

        VerifyCard verifyCard = verifyCardRepository.findById(verifyCardId).orElseThrow(() -> {
            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
        });

        User user = verifyCard.getUser();

        if (user.getVerify() == Verify.TRUE) {
            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
        }

        return user;
    }
}
