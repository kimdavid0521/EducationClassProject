package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.converter.VerifyCardConverter;
import com.example.EducationClassProject.domain.VerifyCard;
import com.example.EducationClassProject.dto.verifyDTO.VerifyRequestDTO;
import com.example.EducationClassProject.dto.verifyDTO.VerifyResponseDTO;
import com.example.EducationClassProject.service.VerifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VerifyCardController {

    private final VerifyService verifyService;

    // 인증서 신청 보내기
    @PostMapping("/verifyCard/{userId}") // jwt 추가 후 수정해야됨.
    public BaseResponse<String> applyVerify(@PathVariable UUID userId, @RequestBody VerifyRequestDTO.ApplyVerifyDTO applyVerifyDTO) {
        VerifyCard verifyCard = verifyService.applyVerify(userId, applyVerifyDTO);
        return BaseResponse.onSuccess("선생 검증 요청을 보냈습니다");
    }


    // 인증서 내역 전체 조회 ( 관리자 페이지 )
    @GetMapping("/verifyCard")
    public BaseResponse<VerifyResponseDTO.PreviewVerifyCardListDTO> previewVerifyCardList() {
        List<VerifyCard> verifyCardList = verifyService.previewVerifyCardList();
        return BaseResponse.onSuccess(VerifyCardConverter.previewVerifyCardList(verifyCardList));
    }

    // 인증서 내역 조회 ( 미수락, 수락 )

    // 인증서 수락 ( 관리자 페이지 )
    @PatchMapping("/verify/accept/{verifyCardId}")
    public BaseResponse<VerifyResponseDTO.PreviewVerifyCardDTO> acceptVerify(@PathVariable Long verifyCardId) {
        VerifyCard verifyCard = verifyService.acceptVerifyCard(verifyCardId);
        return BaseResponse.onSuccess(VerifyCardConverter.previewVerifyCardDTO(verifyCard));
    }


}
