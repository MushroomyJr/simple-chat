package com.simple_chat.chat_service.controllers;

import com.simple_chat.chat_service.DTOs.LoginUserDto;
import com.simple_chat.chat_service.DTOs.UserDto;
import com.simple_chat.chat_service.entity.User;
import com.simple_chat.chat_service.service.JwtService;
import com.simple_chat.chat_service.service.UserService;
import com.simple_chat.chat_service.util.PasswordUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private int DAYS_TO_MILLI = 24 * 60 * 60 * 1000;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController (UserService userService, JwtService jwtService){
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto){
        User user = registerNewUser(userDto);
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, createHttpOnlyCookie("refreshToken",refreshToken, 7))
                .body(Map.of("jwtToken", jwtToken));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDto loginUserDto){
        User user = userService.findByUserName(loginUserDto.getUserName())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        if (!PasswordUtil.verifyPassword(user.getPassword(), loginUserDto.getPassword()))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("invalid credentials");

        String refreshToken = jwtService.generateRefreshToken(user);
        String jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, createHttpOnlyCookie("refreshToken", refreshToken, 7))
                .body(Map.of("jwtToken", jwtToken));
    }

    private String createHttpOnlyCookie(String name, String value, int days){
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .sameSite("Strict")
                .secure(true)
                .path("/")
                .maxAge(days * DAYS_TO_MILLI)
                .build().toString();
    }

    private User registerNewUser (UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(PasswordUtil.hashedPassword(userDto.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        userService.save(user);

        return user;
    }
}
