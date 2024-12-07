package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUserName(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findUsersByUsernames(List<String> usernames) {
        return userRepository.findByUserNameIn(usernames);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
