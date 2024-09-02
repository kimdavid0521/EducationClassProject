package com.example.EducationClassProject.dto.classDTO;

import com.example.EducationClassProject.domain.enums.ClassDay;
import com.example.EducationClassProject.domain.enums.ClassLevel;
import com.example.EducationClassProject.domain.enums.Test;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ClassRequestDTO {

    // 강의 생성 dto
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateClassDTO {
        private String className;
        private String classIntro;
        private String classExplain;
        private ClassLevel classLevel;
        private ClassDay classDay;
        private LocalDateTime classStartDay;
        private Test classTest;

    }
}
