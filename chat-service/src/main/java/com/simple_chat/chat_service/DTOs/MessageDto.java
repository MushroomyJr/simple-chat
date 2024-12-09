package com.simple_chat.chat_service.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class MessageDto {
    @NotBlank
    @Getter
    @Setter
    private Long senderId;
    @NotBlank
    @Getter
    @Setter
    private String content;
}
