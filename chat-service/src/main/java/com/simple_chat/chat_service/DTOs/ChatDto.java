package com.simple_chat.chat_service.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ChatDto {
    @NotBlank(message = "chat name can not be blank")
    @Getter
    @Setter
    private String name;

    @NotEmpty(message = "participants required for chat")
    @Getter
    @Setter
    private List<String> participantNames;

}
