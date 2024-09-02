package com.example.EducationClassProject.apiPayload.code.status;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버에러, 관리자 문의."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증 필요."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // user 에러
    _NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER400", "유저를 찾을 수 없습니다."),

    // class 에러
    _NOT_FOUND_CLASS(HttpStatus.NOT_FOUND, "CLASS400", "클래스를 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder().message(message).code(code).isSuccess(false).build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder().
                httpStatus(httpStatus).
                message(message).
                code(code).
                isSuccess(false).
                build();
    }
}
