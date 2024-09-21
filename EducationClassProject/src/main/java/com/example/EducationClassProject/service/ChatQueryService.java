package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatQueryService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatMessage chatMessage);

    // 채팅 기록 조회
    ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, String token);
}
