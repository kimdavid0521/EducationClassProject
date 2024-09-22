package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.service.ChatCommandService;
import com.example.EducationClassProject.service.ChatQueryService;
import com.example.EducationClassProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatContoller {

    private final ChatService chatService;
    private final ChatCommandService chatCommandService;
    private final ChatQueryService chatQueryService;

    // 메세지 전송 및 저장
    @MessageMapping("/chat/message")
    public BaseResponse<ChatResponseDTO.ChatMessageResponseDTO> sendMessage(@RequestBody ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, @RequestHeader("Authorization") String token) {
        ChatMessage chatMessage = chatCommandService.saveMessage(sendChatMessageDTO, token); // 메세지 저장
        return BaseResponse.onSuccess(chatQueryService.sendMessage(chatMessage));
    }

    // 채팅 기록 조회
    @GetMapping("/chat/{roomId}")
    public BaseResponse<ChatResponseDTO.ChatMessageListResponseDTO> getChatHistory(@PathVariable Long roomId, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatQueryService.getChatHistory(roomId, token));
    }

    // 채팅방 생성
    @PostMapping("/chat/chatroom")
    public BaseResponse<ChatResponseDTO.MakeChatRoomResponseDTO> makeChatroom(@RequestBody ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, @RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatCommandService.makeChatRoom(makeChatroomRequestDTO, token));
    }

    // 채팅방 입장 ( 비밀번호 없는 채팅방 )
    @PostMapping("/chat/join/{roomId}")
    public BaseResponse<String> joinChatroom(@PathVariable Long roomId, @RequestHeader("Authorization") String token) {
        Chatroom chatroom = chatQueryService.findChatroom(roomId);
        Long joinRoomId = chatCommandService.joinChatRoom(token, chatroom);
        return BaseResponse.onSuccess(joinRoomId + "번 방에 참여하셨습니다.");
    }

    // 채팅방 입장 ( 비밀번호 있는 채팅방 )
    @PostMapping("/chat/join/secret/{roomId}")
    public BaseResponse<String> joinSecretChatroom(@RequestBody ChatRequestDTO.JoinSecretChatroomDTO joinSecretChatroomDTO, @PathVariable Long roomId, @RequestHeader("Authorization") String token) {
        Long joinRoomId = chatService.joinSecretChatroom(joinSecretChatroomDTO, roomId, token);
        return BaseResponse.onSuccess(joinRoomId + "번 방에 참여하셨습니다.");
    }

    // 전체 채팅방 조회
    @GetMapping("/all/chatroom")
    public BaseResponse<ChatResponseDTO.PreviewChatroomListDTO> getAllChatroom(@RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatService.getAllChatroom(token));
    }

    // 사용자가 입장되어있는 채팅방 조회
    @GetMapping("/my/chatroom")
    public BaseResponse<ChatResponseDTO.PreviewChatroomListDTO> previewMyChatroom(@RequestHeader("Authorization") String token) {
        return BaseResponse.onSuccess(chatService.previewMyChatroom(token));
    }

    // 채팅방 나가기
    @PostMapping("/chatroom/out/{roomId}")
    public BaseResponse<String> outChatroom(@PathVariable Long roomId, String token) {
        return BaseResponse.onSuccess(chatService.outChatroom(roomId, token));
    }



}
