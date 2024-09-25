package com.example.EducationClassProject.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ReasonDTO {  // 응답할 정보 적어주기

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;

}
