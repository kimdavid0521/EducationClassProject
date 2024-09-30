package com.example.EducationClassProject.dto.classDTO;

import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.enums.ClassLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ClassResponseDTO {

    // 강의 생성시 응답값 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateClassResultDTO {
        private Long classId;
        private String className;
        private String classIntro;
    }

    //강의 객체 조회시 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewClassResultDTO {
        private Long classId;
        private String className;
        private String classIntro;
        private String classExplain;
        private ClassLevel classLevel;
    }

    // 강의 조회 리스트 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewClassListResultDTO {
        List<PreviewClassResultDTO> previewClassResultDTOList;
        int totalPages; // 전체 페이지 수
        long totalElements; // 전체 항목 수
        int currentPage; // 현재 페이지 번호
        int pageSize; // 페이지당 항목 수
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResultFindClass {
        Lecture aLecture;
        User user;
    }

}
