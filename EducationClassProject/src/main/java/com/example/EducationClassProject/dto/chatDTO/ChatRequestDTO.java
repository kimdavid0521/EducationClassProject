package com.example.EducationClassProject.dto.chatDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatRequestDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SendChatMessageDTO {
        private String message;
        private Long chatroomId;
    }
}
