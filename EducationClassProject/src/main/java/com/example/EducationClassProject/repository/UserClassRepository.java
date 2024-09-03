package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.mapping.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserClassRepository extends JpaRepository<UserClass, Long> {

}
