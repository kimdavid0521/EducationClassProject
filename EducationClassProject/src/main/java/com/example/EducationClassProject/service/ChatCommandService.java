package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;

public interface ChatCommandService {

    // 채팅 저장
    ChatMessage saveMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token);
}
