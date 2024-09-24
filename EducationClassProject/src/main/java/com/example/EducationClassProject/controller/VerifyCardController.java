package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.converter.VerifyCardConverter;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.VerifyCommandService;
import com.example.EducationClassProject.service.VerifyQueryService;
import com.example.EducationClassProject.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VerifyCardController {

    private final VerifyQueryService verifyQueryService;
    private final VerifyCommandService verifyCommandService;
    private final VerifyService verifyService;

    // 인증서 신청 보내기
    @PostMapping("/verifyCard")
    public BaseResponse<String> applyVerify(@RequestBody VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO, @AuthUser User user) {
        User resultUser = verifyQueryService.verifyUser(user);
        VerifyCard verifyCard = verifyCommandService.requestVerify(resultUser, applyVerifyDTO);
        return BaseResponse.onSuccess("선생 검증 요청을 보냈습니다");
    }

    // 개인 인증서 조회하기
    @GetMapping("/verifyCard")
    public BaseResponse<VerifyResponseDTO.PreviewVerifyCardDTO> previewVerifyCard(@AuthUser User user) {
        return BaseResponse.onSuccess(verifyQueryService.previewVerifyCard(user));
    }

    // 인증서 내역 전체 조회 ( 관리자 페이지 1: 전체 조회, 2: 수락된 인증서 조회, 3: 미수락된 인증서 조회 )
    @GetMapping("/verifyCard/{typeNum}")
    public BaseResponse<VerifyResponseDTO.PreviewVerifyCardListDTO> previewVerifyCardList(@PathVariable Integer typeNum) {
        return BaseResponse.onSuccess(verifyQueryService.previewVerifyRequestList(typeNum));
    }


    // 인증서 수락 ( 관리자 페이지 )
    @PatchMapping("/verify/accept/{verifyCardId}")
    public BaseResponse<String> acceptVerify(@PathVariable Long verifyCardId) {
        User user = verifyQueryService.getVerifyCardUserForUpdate(verifyCardId);
        verifyCommandService.acceptUserVerifyState(user);
        return BaseResponse.onSuccess("해당 유저의 검증이 수락되었습니다.");
    }

}
