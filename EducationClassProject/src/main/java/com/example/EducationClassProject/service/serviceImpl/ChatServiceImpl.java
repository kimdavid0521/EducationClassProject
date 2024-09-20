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
import com.example.EducationClassProject.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JWTUtil jwtUtil;
    private final ChatroomRepository chatroomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserChatRepository userChatRepository;


    // 메세지 저장 및 전송
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

    // 채팅 기록 조회
    @Override
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

    // 채팅방 생성
    @Override
    public ChatResponseDTO.MakeChatRoomResponseDTO makeChatroom(ChatRequestDTO.MakeChatroomRequestDTO makeChatroomRequestDTO, String token) {

        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

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

    // 비밀번호 없는 채팅방 입장
    @Override
    public Long joinChatroom(Long roomId, String token) {

        String AccessToken = token.replace("Bearer ","");
        User user = jwtUtil.getUserFromToken(AccessToken);

        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> {
            throw new ChatHandler(ErrorStatus._NOT_FOUND_CHATROOM);
        });

        boolean isAlreadyJoin = userChatRepository.existsByUserAndChatroom(user, chatroom);
        if (isAlreadyJoin) {
            throw new ChatHandler(ErrorStatus._ALREADY_JOIN_USER);
        }

        UserChat userChat = UserChat.builder()
                .user(user)
                .chatroom(chatroom)
                .build();

        userChatRepository.save(userChat);

        return chatroom.getId();
    }
}
