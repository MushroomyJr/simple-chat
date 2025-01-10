package com.simple_chat.chat_service.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class MessageDto {
    @NotNull
    @Getter
    @Setter
    private Long senderId;
    @NotBlank
    @Getter
    @Setter
    private String content;
}
