package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ClassController {
    //solid 원칙 리팩토링

    private final ClassService classService;
    private final ClassQueryService classQueryService;
    private final ClassCommandService classCommandService;

    // class 생성 ( 선생 )
    // ( class 생성시 200 포인트 차감 되게 설정 )
    @PostMapping("/class")
    public BaseResponse<ClassResponseDTO.CreateClassResultDTO> createClass(@RequestBody ClassRequestDTO.CreateClassDTO createClassDTO, @AuthUser User user) {
        return BaseResponse.onSuccess(classCommandService.createClass(createClassDTO, user)); // solid 원칙
    }

    // class join 하기 ( 학생 )
    // ( class 참여시 100 포인트 차감되게 설정 )
    @PostMapping("/join/{classId}")
    public BaseResponse<String> joinClass(@PathVariable Long classId, @AuthUser User user) {
        ClassResponseDTO.ResultFindClass resultFindClass = classQueryService.findClass(classId, user);
        Long joinClassId = classCommandService.joinClass(resultFindClass);
        return BaseResponse.onSuccess("강의에 참여했습니다. 강의실 id: " + joinClassId);
    }

    // 유저 id로 class 찾기
    @GetMapping("/class")
    public BaseResponse<ClassResponseDTO.PreviewClassListResultDTO> findClassesByUser(@AuthUser User user) {
        return BaseResponse.onSuccess(classQueryService.findClassesByUser(user)); // solid 원칙
    }

    // 내가 생성한 class 조회하기 ( 선생 )
    @GetMapping("/class/owner")
    public BaseResponse<ClassResponseDTO.PreviewClassListResultDTO> findClassesByOwner(@AuthUser User user) {
        return BaseResponse.onSuccess(classQueryService.findClassesByOwner(user));
    }

    // class 전체 조회
    @GetMapping("/classes")
    public BaseResponse<ClassResponseDTO.PreviewClassListResultDTO> findAllClasses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return BaseResponse.onSuccess(classQueryService.findAllClasses(pageable));
    }

    // 강의 삭제 ( owner 만 허용 )
    @DeleteMapping("/delete/class/{classId}")
    public BaseResponse<String> deleteClass(@PathVariable Long classId, @AuthUser User user) {
        Class aClass = classQueryService.getOwnerClass(classId, user);
        classCommandService.deleteClass(aClass);
        return BaseResponse.onSuccess("삭제 되었습니다.");
    }

    // 강의 정보 수정 ( owner 만 허용 )
    @PatchMapping("/class/{classId}")
    public BaseResponse<ClassResponseDTO.PreviewClassResultDTO> updateClass(@PathVariable Long classId, @RequestBody ClassRequestDTO.UpdateClassDTO updateClassDTO, @AuthUser User user) {
        Class aClass = classQueryService.getOwnerClass(classId, user);
        return BaseResponse.onSuccess(classCommandService.updateClass(aClass, updateClassDTO));
    }


}
