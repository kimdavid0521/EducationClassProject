package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public BaseResponse<String> test() {
        return BaseResponse.onSuccess("성공");
    }
}
