package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    // 이미 채팅방에 유저가 join 되어 있는지 검증
    boolean existsByUserAndChatroom(User user, Chatroom chatroom);
}
