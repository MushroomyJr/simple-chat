package com.simple_chat.chat_service.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class LoginUserDto {
    @Getter
    @Setter
    @NotBlank(message = "user name is required for login")
    private String userName;
    @Getter
    @Setter
    @NotBlank(message = "please provide password")
    private String password;
}
