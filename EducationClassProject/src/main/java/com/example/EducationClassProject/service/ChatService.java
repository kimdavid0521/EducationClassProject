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

    // 채팅방 입장 (비밀번호 없는 채팅방)
    Long joinChatroom(Long roomId, String token);
}
