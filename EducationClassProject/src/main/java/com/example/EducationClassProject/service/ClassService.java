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
}
