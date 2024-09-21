package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatCommandService {

    // 채팅 저장
    ChatMessage saveMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token);

    // 채팅방 생성
    ChatResponseDTO.MakeChatRoomResponseDTO makeChatRoom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, String token);
}
