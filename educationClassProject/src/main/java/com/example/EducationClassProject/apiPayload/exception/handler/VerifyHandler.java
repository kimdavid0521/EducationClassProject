package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class VerifyHandler extends GeneralException {
    public VerifyHandler(BaseErrorCode code) {
        super(code);
    }
}
