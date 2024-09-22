package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ChatHandler;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import com.example.EducationClassProject.dto.chatDTO.ChatRequestDTO;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ChatMessageRepository;
import com.example.EducationClassProject.repository.ChatroomRepository;
import com.example.EducationClassProject.repository.UserChatRepository;
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
    private final UserChatRepository userChatRepository;

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

    // 채팅방 생성
    @Override
    @Transactional
    public ChatResponseDTO.MakeChatRoomResponseDTO makeChatRoom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, String token) {

        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        // 비밀번호 설정 검증
        if (makeChatroomRequestDTO.isSecret() && (makeChatroomRequestDTO.getPassword() == null || makeChatroomRequestDTO.getPassword().isEmpty())) {
            throw new ChatHandler(ErrorStatus._NONE_PASSWORD);
        }

        Chatroom chatroom = Chatroom.builder()
                .name(makeChatroomRequestDTO.getRoomName())
                .isSecret(makeChatroomRequestDTO.isSecret())
                .password(makeChatroomRequestDTO.getPassword())
                .peopleNum(0)
                .owner(user)
                .build();

        chatroomRepository.save(chatroom);

        return ChatResponseDTO.MakeChatRoomResponseDTO.builder()
                .roomName(chatroom.getName())
                .owner(chatroom.getOwner())
                .isSecret(chatroom.isSecret())
                .password(chatroom.getPassword())
                .build();


    }

    @Override
    public Long joinChatRoom(String token, Chatroom chatroom) {
        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        boolean isAlreadyJoin = userChatRepository.existsByUser_IdAndChatroom_Id(user.getId(), chatroom.getId());

        if (isAlreadyJoin) {
            throw new ChatHandler(ErrorStatus._ALREADY_JOIN_USER);
        }

        UserChat userChat = UserChat.builder()
                .user(user)
                .chatroom(chatroom)
                .build();

        userChatRepository.save(userChat);

        return userChat.getChatroom().getId();
    }
}
