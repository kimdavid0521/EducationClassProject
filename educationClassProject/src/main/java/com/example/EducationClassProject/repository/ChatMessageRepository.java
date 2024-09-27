package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    // 채팅방 id로 모든 메세지 찾기 (촤신순)
    Page<ChatMessage> findByChatroom_IdOrderByCreateAtDesc(Long chatroomId, Pageable pageable);
}
