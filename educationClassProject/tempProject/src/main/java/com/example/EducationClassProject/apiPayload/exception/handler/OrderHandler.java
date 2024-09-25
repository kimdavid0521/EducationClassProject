package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class OrderHandler extends GeneralException {
    public OrderHandler(BaseErrorCode code) {
        super(code);
    }
}
