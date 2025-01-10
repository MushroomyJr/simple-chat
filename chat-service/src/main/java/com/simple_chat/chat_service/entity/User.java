package com.simple_chat.chat_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String userName;
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;
    @Getter
    @Setter
    @Column(nullable = false)
    private String password;
    @Getter
    @Setter
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;


}
