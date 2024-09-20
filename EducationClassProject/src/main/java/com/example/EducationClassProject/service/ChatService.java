package com.example.EducationClassProject.service;

import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;

public interface ChatService {

    // 메세지 전송
    ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token);

    // 채팅 내역 조회
    ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, String token);

    // 채팅방 생성
    ChatResponseDTO.MakeChatRoomResponseDTO makeChatroom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, String token);

    // 채팅방 입장 (비밀번호 없는 채팅방)
    Long joinChatroom(Long roomId, String token);

    // 채팅방 입장 (비밀번호 있는 채팅방)
    Long joinSecretChatroom(ChatRequestDTO.JoinSecretChatroomDTO joinSecretChatroomDTO, Long roomId, String token);

    // 전체 채팅방 조회
    ChatResponseDTO.PreviewChatroomListDTO getAllChatroom(String token);

    // 사용자가 입장되어있는 채팅방 조회
    ChatResponseDTO.PreviewChatroomListDTO previewMyChatroom(String token);

    // 채팅방 나가기
    String outChatroom(Long roomId, String token);
}
