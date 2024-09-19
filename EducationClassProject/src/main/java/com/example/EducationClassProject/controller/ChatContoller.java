package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatContoller {

//    @MessageMapping("/chat/message")
//    @SendTo("/topic/{chatroomId}")
//    public BaseResponse<ChatResponseDTO.ChatMessageResponseDTO> sendMessage(@RequestBody ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, @RequestHeader("Authorization") String token, @PathVariable Long chatroomId) {
//
//    }
}
