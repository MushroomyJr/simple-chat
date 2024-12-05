package com.simple_chat.chat_service.controllers;

import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.service.UserService;
import com.simple_chat.chat_service.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class controller {
    @Autowired
    UserService userRepository;

    @GetMapping("/")
    public String homePage(){
        return "Hello to the Simple Chat app!";
    }

    @PostMapping("users/register")
    public String registerPage(@RequestParam(value = "userName")String userName,
                               @RequestParam(value = "email")String email,
                               @RequestParam(value = "password")String password
    ){
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        return "Hi "+userName+", We will get you up and chatting in no time, just need to get you registered first!";
    }

    @PostMapping("users/login")
    public String loginPage(@RequestParam(value = "user_name")String user_name,
                            @RequestParam(value = "password")String password){
        Optional<User> user;
        user = userRepository.findByUserName(user_name);
        if(user.isPresent() && (PasswordUtil.verifyPassword(user.get().getPassword(), password))) return "Welcome back, "+ user_name;
        else return "we could not properly identify you";
    }

    @GetMapping("/users/forgotEmail")
    public String forgotEmail(@RequestParam(value = "user_name")String user_name){
        Optional<User> user = userRepository.findByUserName(user_name);
        if(user.isPresent()) return "your email is: "+user.get().getEmail();
        else return "no user found with name: "+user_name;
    }

    @GetMapping("/users/forgotUserName")
    public String forgotUserName(@RequestParam(value = "email")String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()) return "your user_name is: "+user.get().getUserName();
        else return "no user found with email"+ email;
    }

    @GetMapping("/error")
    public String errorPage(){
        return "Whoops! we seemed to have run into a problem";
    }
}
