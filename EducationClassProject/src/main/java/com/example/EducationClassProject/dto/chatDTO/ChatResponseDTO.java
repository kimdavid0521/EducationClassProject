package com.example.EducationClassProject.dto.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
