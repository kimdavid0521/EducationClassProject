package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ChatHandler;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ChatMessageRepository;
import com.example.EducationClassProject.repository.ChatroomRepository;
import com.example.EducationClassProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JWTUtil jwtUtil;
    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;


    @Override
    public ChatResponseDTO.ChatMessageResponseDTO sendMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token) {
        // 유저 추출
        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        // 채팅방 추출
        Long roomId = sendChatMessageDTO.getChatroomId();
        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> {
            throw new ChatHandler(ErrorStatus._NOT_FOUND_CHATROOM);
        });

        // 메세지 저장
        ChatMessage chatMessage = ChatMessage.builder()
                .content(sendChatMessageDTO.getContent())
                .chatroom(chatroom)
                .sender(user)
                .build();

        chatMessageRepository.save(chatMessage);

        String destination = "/topic" + sendChatMessageDTO.getChatroomId(); // sendTo를 컨트롤러단에서 처리하는게 아닌 서비스단에서 처리 ( 동적인 메세지 전송을 위함 )
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
