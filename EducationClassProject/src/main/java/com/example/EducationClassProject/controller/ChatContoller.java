package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatContoller {

    private final ChatService chatService;

    // 메세지 전송 및 저장
    @MessageMapping("/chat/message")
    public BaseResponse<ChatResponseDTO.ChatMessageResponseDTO> sendMessage(@RequestBody ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatService.sendMessage(sendChatMessageDTO, token));
    }

    // 채팅 기록 조회
    @GetMapping("/chat/{roomId}")
    public BaseResponse<ChatResponseDTO.ChatMessageListResponseDTO> getChatHistory(@PathVariable Long roomId, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatService.getChatHistory(roomId, token));
    }

    // 채팅방 생성
    @PostMapping("/chat/chatroom")
    public BaseResponse<ChatResponseDTO.MakeChatRoomResponseDTO> makeChatroom(@RequestBody ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatService.makeChatroom(makeChatroomRequestDTO, token));
    }



}
