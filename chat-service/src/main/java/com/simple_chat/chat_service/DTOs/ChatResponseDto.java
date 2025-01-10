package com.simple_chat.chat_service.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatResponseDto {
    private Long id;
    private String name;
    private List<ParticipantResponseDto> participants;

    public ChatResponseDto(Long id, String name, List<ParticipantResponseDto> participants) {
        this.id = id;
        this.name = name;
        this.participants = participants;
    }

    // Nested DTO for participant details
    @Getter
    @Setter
    public static class ParticipantResponseDto {
        private Long id;
        private String userName;

        public ParticipantResponseDto(Long id, String userName) {
            this.id = id;
            this.userName = userName;
        }
    }
}