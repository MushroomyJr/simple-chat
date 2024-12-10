package com.simple_chat.chat_service.controllers;

import com.simple_chat.chat_service.DTOs.ChatDto;
import com.simple_chat.chat_service.DTOs.ChatResponseDto;
import com.simple_chat.chat_service.DTOs.MessageDto;
import com.simple_chat.chat_service.entity.Chat;
import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.repository.ChatRepository;
import com.simple_chat.chat_service.repository.UserRepository;
import com.simple_chat.chat_service.service.ChatService;
import com.simple_chat.chat_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/chat")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {

    private final UserService userService;
    private final ChatService chatService;

    public ChatController(ChatService chatService, UserService userService){
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<?> createChat(@Valid @RequestBody ChatDto chatDto){
        chatService.createChat(chatDto);
        return ResponseEntity.ok("Chat was created");
    }

    @PutMapping("{chatId}/edit/name")
    @ResponseBody
    public ResponseEntity<?> editGroup(@PathVariable Long chatId,@Valid @RequestBody ChatDto chatDto){
        Chat updatedChat = chatService.updateName(chatId, chatDto.getName());
        return ResponseEntity.ok().body(updatedChat);
    }

    @PostMapping("/{chatId}/addMessage")
    @ResponseBody
    public ResponseEntity<?> addMessage(@PathVariable Long chatId, @Valid @RequestBody MessageDto message){
        Chat updatedChat = chatService.addMessage(chatId, message);
        return ResponseEntity.ok().body(updatedChat.toString());
    }

    @PutMapping("/{chatId}/edit/participant")
    @ResponseBody
    public ResponseEntity<?> editParticipant(@PathVariable Long chatId, @Valid @RequestBody ChatDto chatDto){
        Chat updatedChat = chatService.updateParticipant(chatId, chatDto);
        return ResponseEntity.ok().body(updatedChat.toString());
    }

    @GetMapping("/{chatId}")
    @ResponseBody
    public ResponseEntity<?> getChat(@PathVariable Long chatId){
        Chat chat = chatService.getChat(chatId);
        return ResponseEntity.ok().body(chat);
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public ResponseEntity<?> getUsersChats(@PathVariable Long userId){
        List<ChatResponseDto> usersChats = chatService.getUsersChats(userId);
        return ResponseEntity.ok().body(usersChats);
    }
}
