package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.mapping.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
}
