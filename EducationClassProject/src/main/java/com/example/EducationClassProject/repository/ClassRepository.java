package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}
