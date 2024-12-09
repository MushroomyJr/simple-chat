package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.DTOs.ChatDto;
import com.simple_chat.chat_service.DTOs.MessageDto;
import com.simple_chat.chat_service.entity.Chat;
import com.simple_chat.chat_service.entity.Message;
import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatRepository chatRepository;
    private UserService userService;
    public ChatServiceImpl(ChatRepository chatRepository, UserService userService){
        this.chatRepository = chatRepository;
        this.userService = userService;
    }
    @Override
    public Chat createChat(ChatDto chatDto) {
        List<User> participants = userService.findUsersByUsernames(chatDto.getParticipantNames());
        if(participants.isEmpty())
            throw new IllegalArgumentException("no valid participants found");
        List<Long> participantId = participants.stream()
                .map(User :: getId)
                .toList();
        Chat chat = new Chat();
        chat.setParticipantIds(participantId);
        chat.setName(chatDto.getName());

        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getUserChats(Long userId) {
        return chatRepository.findAll().stream()
                .filter(chat -> chat.getParticipantIds().contains(userId))
                .toList();
    }

    @Override
    public String lastMessage(Long chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (!chat.isPresent())
            throw new IllegalArgumentException("no such chat exists");
        return "";
    }

    @Override
    public Chat addMessage(Long chatId, MessageDto messageDto) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()->(new IllegalArgumentException("there is no chat with that name")));

        if(!chat.getParticipantIds().contains(messageDto.getSenderId()))
            throw new IllegalArgumentException("this sender is not in the chat");
        Message message = new Message();
        message.setChat(chat);
        message.setContent(messageDto.getContent());
        message.setSenderId(messageDto.getSenderId());
        message.setTimestamp(LocalDateTime.now());
        message.setMessageIndex(chat.getChatSize()+1);
        chat.getMessages().add(message);
        chat.setChatSize(chat.getChatSize()+1);
        return chatRepository.save(chat);

    }

    @Override
    public Chat updateName(Long chatId, String newName){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new IllegalArgumentException("cannot find chat with id: "+ chatId));
        chat.setName(newName);
        return chatRepository.save(chat);
    }

    @Override
    public Chat updateParticipant(Long chatId, ChatDto chatDto) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new IllegalArgumentException("can not find chat with id: "+chatId));
        List <User> users = userService.findUsersByUsernames(chatDto.getParticipantNames());
        System.out.println(users.toString());
        List <Long> userIds = users.stream()
                        .map(User :: getId)
                                .toList();
        chat.setParticipantIds(new ArrayList<>(userIds));
        System.out.println(chat.toString());
        return chatRepository.save(chat);
    }
}
