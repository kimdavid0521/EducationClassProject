package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ClassService {


    // 클래스 생성
    Class createClass(ClassRequestDTO.CreateClassDTO createClassDTO, UUID userId);

    // 유저 id로 클래스 객체 조회
    List<Class> findClassesByUserId(UUID userId);

    // 클래스 전체 조회
    List<Class> findAllClasses();

    // 클래스 삭제
    void deleteClass(Long classId);

    // 클래스 정보 업데이트
    Class updateClass(Long classId, ClassRequestDTO.UpdateClassDTO updateClassDTO);
}
