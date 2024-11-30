package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String homePage(){
        return "Hello to the Simple Chat app!";
    }

    @GetMapping("/register")
    public String registerPage(@RequestParam(value = "userName")String userName,
                               @RequestParam(value = "email")String email,
                               @RequestParam(value = "password")String password
    ){
        User user = new User(userName, email, password);
        userRepository.save(user);
        return "Hi "+userName+", We will get you up and chatting in no time, just need to get you registered first!";
    }

    @GetMapping("/error")
    public String errorPage(){
        return "Whoops! we seemed to have run into a problem";
    }
}
