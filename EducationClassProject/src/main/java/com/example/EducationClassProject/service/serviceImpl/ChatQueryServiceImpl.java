package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ChatHandler;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ChatMessageRepository;
import com.example.EducationClassProject.repository.ChatroomRepository;
import com.example.EducationClassProject.service.ChatQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatQueryServiceImpl implements ChatQueryService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomRepository chatroomRepository;
    private final JWTUtil jwtUtil;

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

    // 채팅 기록 조회
    @Override
    @Transactional(readOnly = true)
    public ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, String token) {

        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        List<ChatMessage> chatMessages = chatMessageRepository.findByChatroom_IdOrderByCreatedAtDesc(roomId);
        List<ChatResponseDTO.ChatMessageResponseDTO> chatMessageResponseDTOList = chatMessages.stream()
                .map(chatMessage -> ChatResponseDTO.ChatMessageResponseDTO.builder()
                        .chatId(chatMessage.getId())
                        .username(chatMessage.getSender().getUsername())
                        .content(chatMessage.getContent())
                        .chatroomId(chatMessage.getChatroom().getId())
                        .build())
                .collect(Collectors.toList());

        return ChatResponseDTO.ChatMessageListResponseDTO.builder()
                .chatMessageResponseDTOList(chatMessageResponseDTOList)
                .build();
    }

    @Override
    public Chatroom findChatroom(Long roomId) {
        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> {
            throw new ChatHandler(ErrorStatus._NOT_FOUND_CHATROOM);
        });
        return chatroom;
    }


}
