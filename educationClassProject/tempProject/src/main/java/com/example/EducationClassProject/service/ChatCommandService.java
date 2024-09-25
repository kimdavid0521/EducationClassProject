package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatCommandService {

    // 채팅 저장
    ChatMessage saveMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, User user);

    // 채팅방 생성
    ChatResponseDTO.MakeChatRoomResponseDTO makeChatRoom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, User user);

    // 채팅방 입장
    Long joinChatRoom(ChatResponseDTO.ResultFindChatroom resultFindChatroom);

    // 채팅방 입장 ( 비밀번호 있는 채팅방 )
    Long joinSecretChatRoom(ChatResponseDTO.ResultFindChatroom resultFindChatroom, ChatRequestDTO.JoinSecretChatroomDTO joinSecretChatroomDTO);

    // 채팅방 나가기
    String outChatroom(UserChat userChat);
}
