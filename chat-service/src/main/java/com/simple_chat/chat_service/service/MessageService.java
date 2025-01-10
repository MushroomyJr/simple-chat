package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.Message;

import java.util.List;

public interface MessageService {
    public List<Message> findChatMessages(Long chatId);
}
