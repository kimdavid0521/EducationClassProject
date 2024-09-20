package com.example.EducationClassProject.repository;

import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserChatRepository extends JpaRepository<UserChat, Long> {
    // 이미 채팅방에 유저가 join 되어 있는지 검증
    //boolean existsByUserAndChatroom(User user, Chatroom chatroom); // 최적화를 위해 id로만 검증하기로 수정
    boolean existsByUser_IdAndChatroom_Id(UUID userId, Long chatroomId);

    // 해당 사용자가 참여한 채팅방 리스트
    List<Chatroom> findChatroomByUser_Id(UUID userId);

    // 유저와 채팅방 사이의 관계 가져오기
    UserChat findByUser_IdAndChatroom_Id(UUID userId, Long chatroomId);
}
