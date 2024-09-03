package com.example.EducationClassProject.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VerifyCard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 100)
    private String info;

    @Column(nullable = false, length = 40)
    private String grade;

    @Column(nullable = false, length = 100)
    private String career;

    @Column(nullable = false, length = 100)
    private String link;


}
