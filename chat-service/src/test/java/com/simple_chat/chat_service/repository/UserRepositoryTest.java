package com.simple_chat.chat_service.repository;

import com.simple_chat.chat_service.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindByUserName(){
        User user = new User();
        user.setUserName("testuser");
        user.setEmail("testuser@email.com");
        user.setPassword("password1");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByUserName("testuser");
        assertTrue(foundUser.isPresent());
        assertEquals("testuser@email.com", foundUser.get().getEmail());
    }
}
