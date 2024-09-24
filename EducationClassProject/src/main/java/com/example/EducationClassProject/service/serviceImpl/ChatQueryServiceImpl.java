package com.example.EducationClassProject.service.serviceImpl;

import com.example.EducationClassProject.apiPayload.code.status.ErrorStatus;
import com.example.EducationClassProject.apiPayload.exception.handler.ChatHandler;
import com.example.EducationClassProject.domain.ChatMessage;
import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import com.example.EducationClassProject.domain.mapping.UserChat;
import com.example.EducationClassProject.dto.chatDTO.ChatResponseDTO;
import com.example.EducationClassProject.jwt.JWTUtil;
import com.example.EducationClassProject.repository.ChatMessageRepository;
import com.example.EducationClassProject.repository.ChatroomRepository;
import com.example.EducationClassProject.repository.UserChatRepository;
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
    private final UserChatRepository userChatRepository;
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
    public ChatResponseDTO.ChatMessageListResponseDTO getChatHistory(Long roomId, User user) {

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


    // 채팅방 찾아서 해당 채팅방에 유저 있는지 판별 후 유저와 채팅방 리턴
    @Override
    @Transactional(readOnly = true)
    public ChatResponseDTO.ResultFindChatroom findChatroom(Long roomId, User user) {

        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> {
            throw new ChatHandler(ErrorStatus._NOT_FOUND_CHATROOM);
        });

        boolean isAlreadyJoin = userChatRepository.existsByUser_IdAndChatroom_Id(user.getId(), chatroom.getId());

        if (isAlreadyJoin) {
            throw new ChatHandler(ErrorStatus._ALREADY_JOIN_USER);
        }

        return ChatResponseDTO.ResultFindChatroom.builder()
                .chatroom(chatroom)
                .user(user)
                .build();
    }

    // 채팅방 조회 후 존재하는지 판별 (채팅방 나가기)
    @Override
    @Transactional(readOnly = true)
    public UserChat findUserChatForOut(Long roomId, User user) {

        Chatroom chatroom = chatroomRepository.findById(roomId).orElseThrow(() -> {
            throw new ChatHandler(ErrorStatus._NOT_FOUND_CHATROOM);
        });

        boolean isAlreadyJoin = userChatRepository.existsByUser_IdAndChatroom_Id(user.getId(), chatroom.getId());

        if (!isAlreadyJoin) {
            throw new ChatHandler(ErrorStatus._NOT_CHATROOM_MEMBER);
        }

        UserChat userChat = userChatRepository.findByUser_IdAndChatroom_Id(user.getId(), chatroom.getId());

        return userChat;

    }

    // 전체 재팅방 조회
    @Override
    @Transactional(readOnly = true)
    public ChatResponseDTO.PreviewChatroomListDTO getAllChatroom(User user) {

        List<Chatroom> chatroomList = chatroomRepository.findAll();
        List<ChatResponseDTO.PreviewChatroomDTO> chatroomDTOList = chatroomList.stream()
                .map(chatroom -> ChatResponseDTO.PreviewChatroomDTO.builder()
                        .chatroomId(chatroom.getId())
                        .isSecret(chatroom.isSecret())
                        .roomName(chatroom.getName())
                        .ownerName(chatroom.getOwner().getUsername())
                        .peopleNum(chatroom.getPeopleNum())
                        .build())
                .collect(Collectors.toList());
        return ChatResponseDTO.PreviewChatroomListDTO.builder()
                .previewChatroomDTOList(chatroomDTOList)
                .build();
    }

    // 사용자가 참여하고있는 채팅방 조회
    @Override
    @Transactional(readOnly = true)
    public ChatResponseDTO.PreviewChatroomListDTO previewMyChatroom(User user) {

        List<Chatroom> chatroomList = userChatRepository.findChatroomByUser_Id(user.getId());
        List<ChatResponseDTO.PreviewChatroomDTO> chatroomDTOList = chatroomList.stream()
                .map(chatroom -> ChatResponseDTO.PreviewChatroomDTO.builder()
                        .isSecret(chatroom.isSecret())
                        .chatroomId(chatroom.getId())
                        .roomName(chatroom.getName())
                        .ownerName(chatroom.getOwner().getUsername())
                        .peopleNum(chatroom.getPeopleNum())
                        .build())
                .collect(Collectors.toList());

        return ChatResponseDTO.PreviewChatroomListDTO.builder()
                .previewChatroomDTOList(chatroomDTOList)
                .build();
    }

}
