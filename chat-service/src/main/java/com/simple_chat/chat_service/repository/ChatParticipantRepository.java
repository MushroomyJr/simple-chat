package com.simple_chat.chat_service.repository;

import com.simple_chat.chat_service.entity.ChatParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Long> {

    @Query("SELECT cp.chat.id FROM ChatParticipant cp WHERE cp.user.id = :userId")
    Optional<List<Long>> findUsersChats(Long userId);
}
