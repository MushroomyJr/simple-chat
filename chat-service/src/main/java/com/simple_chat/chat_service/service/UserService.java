package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.User;

import java.util.Optional;
import java.util.List;

public interface UserService {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findUsersByUsernames (List<String> usernames);
    void save(User user);
}
