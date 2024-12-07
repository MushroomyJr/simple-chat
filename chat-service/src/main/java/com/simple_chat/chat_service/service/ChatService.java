package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.DTOs.ChatDto;
import com.simple_chat.chat_service.DTOs.MessageDto;
import com.simple_chat.chat_service.entity.Chat;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChatService {
    Chat createChat(ChatDto chatDto);
    List<Chat> getUserChats(Long userId);
    String lastMessage (Long chatId);
    Chat addMessage(Long chatId, MessageDto message);
    Chat updateName(Long chatId, String newName);
}
