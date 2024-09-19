package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token);

    // 채팅 내역 조회
    ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, String token);

    // 채팅방 생성
    ChatResponseDTO.MakeChatRoomResponseDTO makeChatroom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, String token);
}
