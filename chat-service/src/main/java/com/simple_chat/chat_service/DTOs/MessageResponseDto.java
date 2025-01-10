package com.simple_chat.chat_service.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponseDto {
    private Long senderId;
    private Long chatId;
}
