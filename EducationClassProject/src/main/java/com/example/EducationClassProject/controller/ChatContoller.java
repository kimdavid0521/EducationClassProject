package com.example.EducationClassProject.controller;

import com.example.EducationClassProject.apiPayload.BaseResponse;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.global.PrincipalDetails;
import com.example.EducationClassProject.jwt.AuthUser;
import com.example.EducationClassProject.service.ChatCommandService;
import com.example.EducationClassProject.service.ChatQueryService;
import com.example.EducationClassProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatContoller {

    private final ChatService chatService;
    private final ChatCommandService chatCommandService;
    private final ChatQueryService chatQueryService;

    // 메세지 전송 및 저장
    @MessageMapping("/chat/message")
    public BaseResponse<ChatResponseDTO.ChatMessageResponseDTO> sendMessage(@RequestBody ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, @AuthUser User user) {
        ChatMessage chatMessage = chatCommandService.saveMessage(sendChatMessageDTO, user); // 메세지 저장
        return BaseResponse.onSuccess(chatQueryService.sendMessage(chatMessage));
    }

    // 채팅 기록 조회
    @GetMapping("/chat/{roomId}")
    public BaseResponse<ChatResponseDTO.ChatMessageListResponseDTO> getChatHistory(@PathVariable Long roomId, @AuthUser User user) {
        return BaseResponse.onSuccess(chatQueryService.getChatHistory(roomId, user));
    }

    // 채팅방 생성
    @PostMapping("/chat/chatroom")
    public BaseResponse<ChatResponseDTO.MakeChatRoomResponseDTO> makeChatroom(@RequestBody ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, @AuthUser User user) {
        return BaseResponse.onSuccess(chatCommandService.makeChatRoom(makeChatroomRequestDTO, user));
    }

    // 채팅방 입장 ( 비밀번호 없는 채팅방 )
    @PostMapping("/chat/join/{roomId}")
    public BaseResponse<String> joinChatroom(@PathVariable Long roomId, @AuthUser User user) {
        ChatResponseDTO.ResultFindChatroom resultFindChatroom = chatQueryService.findChatroom(roomId, user);
        Long joinRoomId = chatCommandService.joinChatRoom(resultFindChatroom);
        return BaseResponse.onSuccess(joinRoomId + "번 방에 참여하셨습니다.");
    }

    // 채팅방 입장 ( 비밀번호 있는 채팅방 )
    @PostMapping("/chat/join/secret/{roomId}")
    public BaseResponse<String> joinSecretChatroom(@RequestBody ChatRequestDTO.JoinSecretChatroomDTO joinSecretChatroomDTO, @PathVariable Long roomId, @AuthUser User user) {
        ChatResponseDTO.ResultFindChatroom resultFindChatroom = chatQueryService.findChatroom(roomId, user);
        Long joinRoomId = chatCommandService.joinSecretChatRoom(resultFindChatroom, joinSecretChatroomDTO);
        return BaseResponse.onSuccess(joinRoomId + "번 방에 참여하셨습니다.");
    }

    // 전체 채팅방 조회
    @GetMapping("/all/chatroom")
    public BaseResponse<ChatResponseDTO.PreviewChatroomListDTO> getAllChatroom(@AuthUser User user) {
        return BaseResponse.onSuccess(chatQueryService.getAllChatroom(user));
    }

    // 사용자가 입장되어있는 채팅방 조회
    @GetMapping("/my/chatroom")
    public BaseResponse<ChatResponseDTO.PreviewChatroomListDTO> previewMyChatroom(@AuthUser User user) {
        return BaseResponse.onSuccess(chatQueryService.previewMyChatroom(user));
    }

    // 채팅방 나가기
    @PostMapping("/chatroom/out/{roomId}")
    public BaseResponse<String> outChatroom(@PathVariable Long roomId, @AuthUser User user) {
        UserChat userChat = chatQueryService.findUserChatForOut(roomId, user);
        return BaseResponse.onSuccess(chatCommandService.outChatroom(userChat));
    }

}
