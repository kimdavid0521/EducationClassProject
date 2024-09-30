package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

public interface LectureCommandService {

    // 클래스 생성
    ClassResponseDTO.CreateClassResultDTO createClass(ClassRequestDTO.CreateClassDTO createClassDTO, User user);

    // 클래스 참여
    Long joinClass(ClassResponseDTO.ResultFindClass resultFindClass);

    // 클래스 삭제
    void deleteClass(Lecture aLecture);

    // 클래스 정보 업데이트
    ClassResponseDTO.PreviewClassResultDTO updateClass(Lecture aLecture, ClassRequestDTO.UpdateClassDTO updateClassDTO);
}
