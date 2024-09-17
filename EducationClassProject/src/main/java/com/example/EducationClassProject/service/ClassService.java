package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ClassService {


    // 클래스 생성
    ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, String token);

    // 유저 id로 클래스 객체 조회
    ClassResponseDTO.PreviewClassListResultDTO findClassesByUserId(String token);

    // 클래스 전체 조회
    ClassResponseDTO.PreviewClassListResultDTO findAllClasses();

    // 클래스 삭제
    void deleteClass(Long classId);

    // 클래스 정보 업데이트
    ClassResponseDTO.PreviewClassResultDTO updateClass(Long classId, String token, ClassRequestDTO.UpdateClassDTO updateClassDTO);
}
