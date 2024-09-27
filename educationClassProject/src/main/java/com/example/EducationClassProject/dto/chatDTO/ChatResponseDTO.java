package com.example.EducationClassProject.dto.chatDTO;

import com.example.EducationClassProject.domain.Chatroom;
import com.example.EducationClassProject.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ChatResponseDTO {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageResponseDTO {
        private Long chatId;
        private String username;
        private String content;
        private Long chatroomId;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatMessageListResponseDTO {
        List<ChatMessageResponseDTO> chatMessageResponseDTOList;
        int totalPages;
        long totalElements;
        int currentPage;
        int pageSize;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MakeChatRoomResponseDTO {
        private String roomName;
        private User owner;
        private boolean isSecret;
        private String password;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PreviewChatroomDTO {
        private Long chatroomId;
        private String roomName;
        private String ownerName;
        private Integer peopleNum; // 현재 입장되어있는 사람 수
        private boolean isSecret;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PreviewChatroomListDTO {
        List<PreviewChatroomDTO> previewChatroomDTOList;
        int totalPages;
        long totalElements;
        int currentPage;
        int pageSize;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResultFindChatroom {
        Chatroom chatroom;
        User user;
    }
}
