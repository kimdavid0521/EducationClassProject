package com.example.EducationClassProject.converter;

import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

import java.util.List;

public class ClassConverter {

    // 클래스 생성시 응답 converter
    public static ClassResponseDTO.CreateClassResultDTO createClassResultDTO(Class classEntity) {
        return ClassResponseDTO.CreateClassResultDTO.builder()
                .classId(classEntity.getId())
                .className(classEntity.getClassName())
                .classIntro(classEntity.getClassIntro())
                .build();
    }

    // 클래스 조회시 converter
    public static ClassResponseDTO.PreviewClassResultDTO previewClassResultDTO(Class classEntity) {
        return ClassResponseDTO.PreviewClassResultDTO.builder()
                .className(classEntity.getClassName())
                .classIntro(classEntity.getClassIntro())
                .classExplain(classEntity.getClassExplain())
                .classLevel(classEntity.getClassLevel())
                .build();
    }

    // 클래스 리스트 조회시 converter (userId로 찾기)
    public static ClassResponseDTO.PreviewClassListResultDTO toPreviewClassList(List<Class> classList) {
        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = classList.stream()
                .map(ClassConverter::previewClassResultDTO)
                .toList();
        return ClassResponseDTO.PreviewClassListResultDTO.builder()
                .previewClassResultDTOList(classResultDTOList)
                .build();
    }


}
