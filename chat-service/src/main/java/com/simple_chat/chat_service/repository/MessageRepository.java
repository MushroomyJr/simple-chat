package com.simple_chat.chat_service.repository;

import com.simple_chat.chat_service.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

List<Message> findByChatId(Long chatId);
}
