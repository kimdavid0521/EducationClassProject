package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Chatroom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {
    // 전체 채팅방 가져오기
    Page<Chatroom> findAll(Pageable pageable);
}
