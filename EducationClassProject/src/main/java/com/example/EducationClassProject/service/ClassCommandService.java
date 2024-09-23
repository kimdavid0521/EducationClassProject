package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

public interface ClassCommandService {

    // 클래스 생성
    ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, String token);
}
