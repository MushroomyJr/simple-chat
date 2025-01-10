package com.simple_chat.chat_service.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

public class UserDto {
    @Getter
    @Setter
    @NotBlank(message = "user name is required")
    private String userName;
    @Getter
    @Setter
    @Email(message = "must be a valid email")
    @NotBlank(message = "email is required")
    private String email;
    @Getter
    @Setter
    @Size(min = 8, message = "size should be atleast 8 characters long")
    private String password;
}
