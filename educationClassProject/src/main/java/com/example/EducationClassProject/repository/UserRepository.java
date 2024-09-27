package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    // 유저 전체 조회
    Page<User> getAllPage(Pageable pageable);
    Optional<User> findByEmail(String email);

}
