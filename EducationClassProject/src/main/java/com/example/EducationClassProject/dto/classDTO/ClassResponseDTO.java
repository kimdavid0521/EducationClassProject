package com.example.EducationClassProject.dto.classDTO;

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

    //유저 id로 강의 객체 조회시 dto (List 에 연계하려고 생성)
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewClassResultDTO {
        private String className;
        private String classIntro;
        private String classExplain;
        private ClassLevel classLevel;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PreviewClassListResultDTO {
        List<PreviewClassResultDTO> previewClassResultDTOList;
    }
}
