package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.apiPayload.exception.handler.VerifyHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.UserRepository;
import com.example.EducationClassProject.repository.VerifyCardRepository;
import com.example.EducationClassProject.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VerifyServiceImpl implements VerifyService {

    private final UserRepository userRepository;
    private final VerifyCardRepository verifyCardRepository;
    private final JWTUtil jwtUtil;

    // 유저 선생 검증 요청
//    @Override
//    @Transactional
//    public VerifyCard applyVerify(String token, VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO) {
//
////        String AccessToken = token.replace("Bearer ", "");
////        User user = jwtUtil.getUserFromToken(AccessToken);
////
////        // 유저의 검증 상태 확인
////        if (user.getVerify() == Verify.TRUE) {
////            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
////        }
////
////        // verify 요청을 보낸적이 있는지 검증
////        boolean hasVerifyRequest = verifyCardRepository.existsVerifyCardByUserId(user.getId());
////        if (hasVerifyRequest) {
////            throw new UserHandler(ErrorStatus._ALREADY_REQUEST_VERIFY);
////        }
//
//        VerifyCard verifyCard = VerifyCard.builder()
//                .info(applyVerifyDTO.getInfo())
//                .grade(applyVerifyDTO.getGrade())
//                .career(applyVerifyDTO.getCareer())
//                .link(applyVerifyDTO.getLink())
//                .user(user)
//                .build();
//
//        verifyCardRepository.save(verifyCard);
//
//        return verifyCard;
//    }

    // 개인 검증서 조회
//    @Override
//    @Transactional(readOnly = true)
//    public VerifyResponseDTO.PreviewVerifyCardDTO previewVerifyCard(String token) {
//        String AccessToken = token.replace("Bearer ", "");
//        User user = jwtUtil.getUserFromToken(AccessToken);
//
//        VerifyCard verifyCard = user.getVerifyCard();
//        if (verifyCard == null) {
//            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
//        }
//
//        return new VerifyResponseDTO.PreviewVerifyCardDTO(verifyCard.getUser().getUsername(),
//                verifyCard.getUser().getVerify(),
//                verifyCard.getId(),
//                verifyCard.getInfo(),
//                verifyCard.getGrade(),
//                verifyCard.getCareer(),
//                verifyCard.getLink());
//    }

    // 검증 요청 내역 조회 ( 관리자 페이지 )
//    @Override
//    @Transactional(readOnly = true)
//    public VerifyResponseDTO.PreviewVerifyCardListDTO previewVerifyCardList(Integer typeNum) {
//        List<VerifyCard> verifyCardList;
//        if (typeNum.equals(1)) {
//            verifyCardList = verifyCardRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt")); // 검증서 인증 요청 시간순으로 정렬
//        } else if (typeNum.equals(2)) {
//            verifyCardList = verifyCardRepository.findByUserVerify(Verify.TRUE, Sort.by(Sort.Direction.DESC, "createAt"));
//        } else if (typeNum.equals(3)) {
//            verifyCardList = verifyCardRepository.findByUserVerify(Verify.FALSE, Sort.by(Sort.Direction.DESC, "createAt"));
//        } else {
//            throw new VerifyHandler(ErrorStatus._BAD_REQUEST);
//        }
//
//        List<VerifyResponseDTO.PreviewVerifyCardDTO> verifyCardDTOS = verifyCardList.stream()
//                .map(card -> VerifyResponseDTO.PreviewVerifyCardDTO.builder()
//                        .username(card.getUser().getUsername())
//                        .isVerify(card.getUser().getVerify())
//                        .verifyCardId(card.getId())
//                        .info(card.getInfo())
//                        .grade(card.getGrade())
//                        .career(card.getCareer())
//                        .link(card.getLink())
//                        .build())
//                .collect(Collectors.toList());
//
//        return VerifyResponseDTO.PreviewVerifyCardListDTO.builder()
//                .previewVerifyCardDTOList(verifyCardDTOS)
//                .build();
//    }

    // 유저 검증 상태 업데이트
    @Override
    @Transactional
    public VerifyResponseDTO.PreviewVerifyCardDTO acceptVerifyCard(Long verifyCardId) {
        VerifyCard verifyCard = verifyCardRepository.findById(verifyCardId).orElseThrow(() -> {
            throw new VerifyHandler(ErrorStatus._NOT_FOUND_VERIFYCARD);
        });

        User user = verifyCard.getUser();

        if (user.getVerify() == Verify.TRUE) {
            throw new UserHandler(ErrorStatus._ALREADY_VERIFIED_USER);
        }

        user.updateVerify(); // 유저 검증 상태 업데이트

        return new VerifyResponseDTO.PreviewVerifyCardDTO(verifyCard.getUser().getUsername(),
                verifyCard.getUser().getVerify(),
                verifyCard.getId(),
                verifyCard.getInfo(),
                verifyCard.getGrade(),
                verifyCard.getCareer(),
                verifyCard.getLink());
    }
}
