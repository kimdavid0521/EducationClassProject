package com.example.EducationClassProject.domain;

import com.example.EducationClassProject.domain.enums.ClassDay;
import com.example.EducationClassProject.domain.enums.ClassLevel;
import com.example.EducationClassProject.domain.enums.ClassStatus;
import com.example.EducationClassProject.domain.enums.Test;
import com.example.EducationClassProject.dto.classDTO.ClassRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor //셍성자 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) //외부에서 직접 호출 제한
public class Lecture extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String className;

    @Column(nullable = false, length = 100)
    private String classIntro;

    @Column(nullable = false, length = 100)
    private String classExplain;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private ClassLevel classLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClassDay classDay;

    private LocalDateTime classStartDay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "classTest")
    private Test classTest;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private ClassStatus classStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    // 클래스 업데이트
    public void updateClass(ClassRequestDTO.UpdateClassDTO updateClassDTO) {
        this.className = updateClassDTO.getClassName();
        this.classIntro = updateClassDTO.getClassIntro();
        this.classExplain = updateClassDTO.getClassExplain();
        this.classLevel = updateClassDTO.getClassLevel();
        this.classDay = updateClassDTO.getClassDay();
        this.classStartDay = updateClassDTO.getClassStartDay();
        this.classTest = updateClassDTO.getClassTest();
    }


}
