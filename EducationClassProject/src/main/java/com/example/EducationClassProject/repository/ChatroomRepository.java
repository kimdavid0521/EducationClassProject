package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

}
