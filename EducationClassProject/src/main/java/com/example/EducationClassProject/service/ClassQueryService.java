package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

public interface ClassQueryService {

    // 클래스 조회 후 클래스에 속해있는지 검증
    ClassResponseDTO.ResultFindClass findClass(Long classId, String token);

    // 유저가 참여중인 클래스 조회
    ClassResponseDTO.PreviewClassListResultDTO findClassesByUser(String token);

    // 유저가 생성한 클래스 조회 ( 선생 )
    ClassResponseDTO.PreviewClassListResultDTO findClassesByOwner(String token);
}
