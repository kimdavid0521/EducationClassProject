package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public BaseResponse<String> healthCheck() {
        return BaseResponse.onSuccess("healthy");
    }
}
