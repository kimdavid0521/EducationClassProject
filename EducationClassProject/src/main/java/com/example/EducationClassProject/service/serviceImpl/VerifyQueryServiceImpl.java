package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.UserHandler;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.Verify;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.VerifyCardRepository;
import com.example.EducationClassProject.service.VerifyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VerifyQueryServiceImpl implements VerifyQueryService {

    private final VerifyCardRepository verifyCardRepository;
    private final JWTUtil jwtUtil;

    // 유저 상태 검증 및 검증 요청 보낸적 있는지 확인
    @Override
    @Transactional(readOnly = true)
    public User verifyUser(String token) {
        String AccessToken = token.replace("Bearer ", "");
        User user = jwtUtil.getUserFromToken(AccessToken);

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
}
