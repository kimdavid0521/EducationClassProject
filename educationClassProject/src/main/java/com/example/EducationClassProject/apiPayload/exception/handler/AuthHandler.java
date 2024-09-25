package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class AuthHandler extends GeneralException {
    public AuthHandler(BaseErrorCode code) {
        super(code);
    }
}
