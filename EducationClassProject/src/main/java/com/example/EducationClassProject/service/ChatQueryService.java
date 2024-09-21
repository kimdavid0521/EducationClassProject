package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatQueryService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatMessage chatMessage);
}
