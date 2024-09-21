package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.service.ChatQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    // 메세지 전송
    @Override
    @Transactional(readOnly = true)
    public ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatMessage chatMessage) {
        String destination = "/topic" + chatMessage.getChatroom().getId(); // sendTo를 컨트롤러단에서 처리하는게 아닌 서비스단에서 처리 ( 동적인 메세지 전송을 위함 )
        ChatResponseDTO.ChatMessageResponseDTO chatMessageResponseDTO = ChatResponseDTO.ChatMessageResponseDTO.builder()
                .chatId(chatMessage.getId())
                .username(chatMessage.getSender().getUsername())
                .content(chatMessage.getContent())
                .chatroomId(chatMessage.getChatroom().getId())
                .build();

        simpMessagingTemplate.convertAndSend(destination, chatMessageResponseDTO);
        return chatMessageResponseDTO;
    }
}
