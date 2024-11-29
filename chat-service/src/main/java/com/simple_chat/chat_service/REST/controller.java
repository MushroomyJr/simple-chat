package com.simple_chat.chat_service.REST;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/")
    public String homePage(){
        return "Hello to the Simple Chat app!";
    }
}
