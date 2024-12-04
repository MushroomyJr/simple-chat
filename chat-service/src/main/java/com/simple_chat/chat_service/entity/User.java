package com.simple_chat.chat_service.entity;

import com.simple_chat.chat_service.util.PasswordUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    public String userName;
    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    public String email;
    @Getter
    @Setter
    @Column(nullable = false)
    public String password;

    public User() {
    }

    public User(String userName, String email, String password){
        this.userName = userName;
        this.email = email;
        this.password = PasswordUtil.hashedPassword(password);
    }

}
