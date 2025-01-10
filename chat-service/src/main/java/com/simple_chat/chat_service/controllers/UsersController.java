package com.simple_chat.chat_service.controllers;

import com.simple_chat.chat_service.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    @Autowired
    UserServiceImpl userService;
    //here we will implement user's forgetting password or changing password, with future maybe adding profile pictures and status
}
