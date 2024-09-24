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
    public ChatMessage saveMessage(ChatRequestDTO.SendChatMessageDTO sendChatMessageDTO, User user) {

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

    // 채팅방 입장 ( 비밀번호 없는 채팅방 )
    @Override
    @Transactional
    public Long joinChatRoom(ChatResponseDTO.ResultFindChatroom resultFindChatroom) {

        UserChat userChat = UserChat.builder()
                .user(resultFindChatroom.getUser())
                .chatroom(resultFindChatroom.getChatroom())
                .build();

        userChatRepository.save(userChat);

        return userChat.getChatroom().getId();
    }

    // 채팅방 입장 ( 비밀번호 있는 채팅방 )
    @Override
    @Transactional
    public Long joinSecretChatRoom(ChatResponseDTO.ResultFindChatroom resultFindChatroom, ChatRequestDTO.JoinSecretChatroomDTO joinSecretChatroomDTO) {

        if (!resultFindChatroom.getChatroom().isSecret()) {
            throw new ChatHandler(ErrorStatus._NOT_SECRET_ROOM);
        }

        if (!joinSecretChatroomDTO.getPassword().equals(resultFindChatroom.getChatroom().getPassword())) {
            throw new ChatHandler(ErrorStatus._PASSWORD_ERROR);
        }

        UserChat userChat = UserChat.builder()
                .user(resultFindChatroom.getUser())
                .chatroom(resultFindChatroom.getChatroom())
                .build();

        userChatRepository.save(userChat);

        return userChat.getChatroom().getId();

    }

    // 채팅방 나가기
    @Override
    @Transactional
    public String outChatroom(UserChat userChat) {

        userChatRepository.delete(userChat);

        return "방에서 나갔습니다.";
    }


}
