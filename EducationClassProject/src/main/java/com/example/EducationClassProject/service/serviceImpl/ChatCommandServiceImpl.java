package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ChatHandler;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ChatMessageRepository;
import com.example.EducationClassProject.repository.ChatroomRepository;
import com.example.EducationClassProject.service.ChatCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatCommandServiceImpl implements ChatCommandService {

    private final JWTUtil jwtUtil;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatroomRepository chatroomRepository;

    // 메세지 저장
    @Override
    @Transactional
    public ChatMessage saveMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, String token) {
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
        return chatMessage;
    }
}
