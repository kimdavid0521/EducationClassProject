package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Class;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long> {

    // 중복 참여 여부 확인
    boolean existByUserAndAClass(User user, Class aClass);
}
