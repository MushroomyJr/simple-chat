package com.simple_chat.chat_service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Hashing a given password
     * @param password
     * @return
     */
    public static String hashedPassword(String password){
        return passwordEncoder.encode(password);
    }

    /**
     * We are checking if the password provided matches the password which is hashed
     * @param hashedPassword
     * @param checkingPassword
     * @return
     */
    public static boolean  verifyPassword(String hashedPassword, String checkingPassword){
        return passwordEncoder.matches(checkingPassword, hashedPassword);
    }
}
