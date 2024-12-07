package com.simple_chat.chat_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import java.util.ArrayList;

@Entity
@Table(name = "chats")
@Data
public class Chat {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ElementCollection
    @CollectionTable(name = "chat_participants", joinColumns=@JoinColumn(name = "chat_id"))
    @Column(name = "participant_id")
    private List<Long> participantIds = new ArrayList<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new ArrayList<>();

    @Column( nullable = false)
    @Getter
    @Setter
    private String name;

    @Column(nullable = false)
    @Getter
    @Setter
    private int chatSize=0;
}
