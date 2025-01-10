package com.simple_chat.chat_service.service;

import com.simple_chat.chat_service.DTOs.ChatDto;
import com.simple_chat.chat_service.DTOs.ChatResponseDto;
import com.simple_chat.chat_service.DTOs.MessageDto;
import com.simple_chat.chat_service.entity.Chat;
import com.simple_chat.chat_service.entity.ChatParticipant;
import com.simple_chat.chat_service.entity.Message;
import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.repository.ChatParticipantRepository;
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
    private ChatParticipantRepository chatParticipantRepository;

    public ChatServiceImpl(ChatRepository chatRepository, UserService userService, ChatParticipantRepository chatParticipantRepository){
        this.chatRepository = chatRepository;
        this.userService = userService;
        this.chatParticipantRepository = chatParticipantRepository;
    }
    @Override
    public Chat createChat(ChatDto chatDto) {
        Chat chat = new Chat();
        chat.setName(chatDto.getName());

        List<ChatParticipant> chatParticipants = chatDto.getParticipantNames().stream().map(username ->{
            User user = userService.findByUsername(username)
                    .orElseThrow(()->new IllegalArgumentException("username can not be found"));
            ChatParticipant participant = new ChatParticipant();
            participant.setUser(user);
            participant.setChat(chat);
            return participant;
        }).toList();
        //System.out.println("chat participants: "+chatParticipants.get(0).getUser());
        chat.setParticipants(chatParticipants);
        chat.setMessages(List.of());
        chat.setChatSize(0);
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getUserChats(Long userId) {
        return chatRepository.findAll().stream()
                .filter(chat -> chat.getParticipants().contains(userId))
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

//        if(!chat.getParticipants().contains(messageDto.getSenderId()))
//            throw new IllegalArgumentException("this sender is not in the chat "+ chat.getParticipants().get(0).getUser().getUserName());
//
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
        System.out.println(chatId + ", "+chatDto);
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new IllegalArgumentException("can not find chat with id: "+chatId));
        System.out.println("chat: "+chat);
        List <User> users = chatDto.getParticipantNames().
                stream().map(username ->userService.findByUsername(username)
                        .orElseThrow(()->new IllegalArgumentException("can not find user")))
                .toList();
        List<ChatParticipant> chatParticipants = new ArrayList<>();
        for(User user: users) {
            ChatParticipant participant = new ChatParticipant();
            participant.setUser(user);
            participant.setChat(chat);
            chatParticipants.add(participant);
        }
        chat.getParticipants().clear();
        chat.getParticipants().addAll(chatParticipants);
        chat.setName(chatDto.getName());
        System.out.println("chat name: "+chat.toString());
        System.out.println("participant size: "+chat.getParticipants().size());
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChat(Long chatId){
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(()-> new IllegalArgumentException("can not find chat with ID: "+chatId));
        return chat;
    }

    @Override
    public List<ChatResponseDto> getUsersChats(Long userId) {
        List<Long> chatIds = chatParticipantRepository.findUsersChats(userId)
                .orElseThrow(()-> new IllegalArgumentException("user does not have chats"));

        List<Chat> chats = chatIds.stream().map(chatId -> chatRepository.findById(chatId)
                .orElseThrow(()->new IllegalArgumentException("chat not found"))).toList();

        return chats.stream().map(chat ->{
            List<ChatResponseDto.ParticipantResponseDto> participantResponseDtos = chat.getParticipants().stream()
                    .map(participant -> new ChatResponseDto.ParticipantResponseDto(participant.getId(), participant.getUser().getUserName()
                    )).toList();

            return new ChatResponseDto(chat.getId(), chat.getName(), participantResponseDtos);
        }).toList();
    }
}
