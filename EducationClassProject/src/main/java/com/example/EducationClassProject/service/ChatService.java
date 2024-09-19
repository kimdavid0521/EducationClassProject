package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token);
}
