package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ClassQueryService {

    // 클래스 조회 후 클래스에 속해있는지 검증
    ClassResponseDTO.ResultFindClass findClass(Long classId, User user);

    // 유저가 참여중인 클래스 조회
    ClassResponseDTO.PreviewClassListResultDTO findClassesByUser(User user, Pageable pageable);

    // 유저가 생성한 클래스 조회 ( 선생 )
    ClassResponseDTO.PreviewClassListResultDTO findClassesByOwner(User user, Pageable pageable);

    // 모든 클래스 조회
    ClassResponseDTO.PreviewClassListResultDTO findAllClasses(Pageable pageable);

    // 사용자가 owner 인지 검증 및 클래스 객체 반환
    Class getOwnerClass(Long classId, User user);
}
