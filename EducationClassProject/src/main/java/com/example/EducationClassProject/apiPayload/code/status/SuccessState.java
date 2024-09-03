package com.example.EducationClassProject.apiPayload.code.status;

import com.example.EducationClassProject.apiPayload.code.BaseCode;
import com.example.EducationClassProject.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessState implements BaseCode {
    _OK(HttpStatus.OK, "COMMON200", "성공"),
    _CREATED(HttpStatus.CREATED, "COMMON201","요청 성공, 리소스 생성");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder().code(code).message(message).isSuccess(true).build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .isSuccess(true)
                .build();
    }
}
