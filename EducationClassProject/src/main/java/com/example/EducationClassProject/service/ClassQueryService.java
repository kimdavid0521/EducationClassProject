package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

public interface ClassQueryService {

    // 클래스 조회 후 클래스에 속해있는지 검증
    ClassResponseDTO.ResultFindClass findClass(Long classId, String token);
}
