package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    // 이미 채팅방에 유저가 join 되어 있는지 검증
    //boolean existsByUserAndChatroom(User user, Chatroom chatroom); // 최적화를 위해 id로만 검증하기로 수정
    boolean existsByUser_IdAndChatroom_Id(UUID userId, Long chatroomId);

}
