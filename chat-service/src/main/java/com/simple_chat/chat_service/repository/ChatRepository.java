package com.simple_chat.chat_service.repository;

import com.simple_chat.chat_service.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    //Optional<ArrayList<Chat>> findAllChatsForUser(Long id);
}
