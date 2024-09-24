package com.example.EducationClassProject.service;

import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatQueryService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatMessage chatMessage);

    // 채팅 기록 조회
    ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, User user);

    // 채팅방 조회
    ChatResponseDTO.ResultFindChatroom findChatroom(Long roomId, User user);

    // 채팅방 조회 (채팅방 나가기)
    UserChat findUserChatForOut(Long roomId, User user);

    // 전체 채팅방 조회
    ChatResponseDTO.PreviewChatroomListDTO getAllChatroom(User user);

    // 사용자가 참여하고있는 채팅방 조회
    ChatResponseDTO.PreviewChatroomListDTO previewMyChatroom(User user);

}
