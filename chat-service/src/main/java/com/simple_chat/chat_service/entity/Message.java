package com.simple_chat.chat_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @Getter
    @Setter
    private Chat chat;

    @Column(nullable = false)
    @Getter
    @Setter
    private Long senderId;

    @Column(nullable = false)
    @Getter
    @Setter
    private String content;

    @Column(nullable = false)
    @Getter
    @Setter
    private int messageIndex;

    @Column(nullable = false)
    @Getter
    @Setter
    private LocalDateTime timestamp;
}
