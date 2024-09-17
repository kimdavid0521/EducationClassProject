package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.converter.ClassConverter;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClassController {
    //solid 원칙 리팩토링

    private final ClassService classService;

    // class 생성
    @PostMapping("/class")
    public BaseResponse<ClassResponseDTO.CreateClassResultDTO> createClass(@RequestBody ClassRequestDTO.CreateClassDTO createClassDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(classService.createClass(createClassDTO, token)); // solid 원칙
    }

    // 유저 id로 class 찾기
    @GetMapping("/class")
    public BaseResponse<ClassResponseDTO.PreviewClassListResultDTO> findClassesByUser(@RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(classService.findClassesByUserId(token)); // solid 원칙
    }

    // class 전체 조회
    @GetMapping("/classes")
    public BaseResponse<ClassResponseDTO.PreviewClassListResultDTO> findAllClasses() {
        return BaseResponse.onSuccess(classService.findAllClasses());
    }

    // 강의 삭제
    @DeleteMapping("/delete/class/{classId}")
    public BaseResponse<String> deleteClass(@PathVariable Long classId) {
        classService.deleteClass(classId);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }

    // 강의 정보 수정
    @PatchMapping("/class/{classId}")
    public BaseResponse<ClassResponseDTO.PreviewClassResultDTO> updateClass(@PathVariable Long classId, @RequestBody ClassRequestDTO.UpdateClassDTO updateClassDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(classService.updateClass(classId, token, updateClassDTO));
    }


}
