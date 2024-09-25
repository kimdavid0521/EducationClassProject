package com.example.EducationClassProject.apiPayload.exception.handler;

import com.example.EducationClassProject.apiPayload.code.BaseErrorCode;
import com.example.EducationClassProject.apiPayload.exception.GeneralException;

public class ChatHandler extends GeneralException {
    public ChatHandler(BaseErrorCode code) {
        super(code);
    }
}
