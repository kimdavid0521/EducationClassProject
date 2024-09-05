package com.example.EducationClassProject.service;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.TestHandler;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    public void failedTest() {
        if (1==1) {
            throw new TestHandler(ErrorStatus._BAD_REQUEST);
        }
    }
}
