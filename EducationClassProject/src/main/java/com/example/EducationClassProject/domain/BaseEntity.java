package com.example.EducationClassProject.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //엔티티 클래스에 공통 속성을 정의하기 위함
@EntityListeners(AuditingEntityListener.class) //엔티티 수정 생성의 이벤트 감지
@Getter
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

}
