package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class ClassHandler extends GeneralException {

    public ClassHandler(BaseErrorCode code) {
        super(code);
    }
}
