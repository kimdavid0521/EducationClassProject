package com.example.EducationClassProject.converter;

import com.example.EducationClassProject.domain.Lecture;
import com.example.EducationClassProject.dto.classDTO.ClassResponseDTO;

import java.util.List;

public class ClassConverter {

    // 클래스 생성시 응답 converter
    public static ClassResponseDTO.CreateClassResultDTO createClassResultDTO(Lecture lectureEntity) {
        return ClassResponseDTO.CreateClassResultDTO.builder()
                .classId(lectureEntity.getId())
                .className(lectureEntity.getClassName())
                .classIntro(lectureEntity.getClassIntro())
                .build();
    }

    // 클래스 조회시 converter
    public static ClassResponseDTO.PreviewClassResultDTO previewClassResultDTO(Lecture lectureEntity) {
        return ClassResponseDTO.PreviewClassResultDTO.builder()
                .className(lectureEntity.getClassName())
                .classIntro(lectureEntity.getClassIntro())
                .classExplain(lectureEntity.getClassExplain())
                .classLevel(lectureEntity.getClassLevel())
                .build();
    }

    // 클래스 리스트 조회시 converter (userId로 찾기)
    public static ClassResponseDTO.PreviewClassListResultDTO toPreviewClassList(List<Lecture> lectureList) {
        List<ClassResponseDTO.PreviewClassResultDTO> classResultDTOList = lectureList.stream()
                .map(ClassConverter::previewClassResultDTO)
                .toList();
        return ClassResponseDTO.PreviewClassListResultDTO.builder()
                .previewClassResultDTOList(classResultDTOList)
                .build();
    }


}
