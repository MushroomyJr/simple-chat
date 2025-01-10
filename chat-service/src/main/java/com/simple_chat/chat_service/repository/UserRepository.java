package com.simple_chat.chat_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.simple_chat.chat_service.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    List<User> findByUserNameIn(List<String> usernames);
}
