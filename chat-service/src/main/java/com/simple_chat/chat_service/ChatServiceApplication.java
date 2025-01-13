package com.simple_chat.chat_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatServiceApplication {

    public static void main(String[] args) {
        // Run the Spring Boot application
        ConfigurableApplicationContext context = SpringApplication.run(ChatServiceApplication.class, args);

        // Log server address and port
        String serverAddress = context.getEnvironment().getProperty("server.address");
        String serverPort = context.getEnvironment().getProperty("server.port");
        System.out.println("Server Address: " + serverAddress);
        System.out.println("Server Port: " + serverPort);

        // Log datasource properties for debugging
        String datasourceUrl = context.getEnvironment().getProperty("spring.datasource.url");
        String datasourceUsername = context.getEnvironment().getProperty("spring.datasource.username");
        System.out.println("Datasource URL: " + datasourceUrl);
        System.out.println("Datasource Username: " + datasourceUsername);

    }
}
