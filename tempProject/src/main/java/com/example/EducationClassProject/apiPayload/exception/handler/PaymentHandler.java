package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class PaymentHandler extends GeneralException {
    public PaymentHandler(BaseErrorCode code) {
        super(code);
    }
}
