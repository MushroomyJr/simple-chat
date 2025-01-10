package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.entity.Message;
import com.simple_chat.chat_service.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    private MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> findChatMessages(Long chatId) {
        System.out.println("we are finding all messages in chat: "+chatId);
        List<Message> chatMessages = messageRepository.findByChatId(chatId);
        return chatMessages;
    }
}
