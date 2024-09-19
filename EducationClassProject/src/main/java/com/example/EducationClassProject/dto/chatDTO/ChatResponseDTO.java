package com.example.EducationClassProject.dto.chatDTO;

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
}
