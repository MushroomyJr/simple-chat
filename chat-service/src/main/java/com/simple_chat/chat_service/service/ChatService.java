package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.DTOs.ChatDto;
import com.simple_chat.chat_service.DTOs.ChatResponseDto;
import com.simple_chat.chat_service.DTOs.MessageDto;
import com.simple_chat.chat_service.entity.Chat;

import java.util.List;

public interface ChatService {
    Chat createChat(ChatDto chatDto);
    Chat getChat(Long chatId);
    List<ChatResponseDto> getUsersChats(Long userId);
    List<Chat> getUserChats(Long userId);
    String lastMessage (Long chatId);
    Chat addMessage(Long chatId, MessageDto message);
    Chat updateName(Long chatId, String newName);
    Chat updateParticipant(Long chatId, ChatDto chatDto);
}
