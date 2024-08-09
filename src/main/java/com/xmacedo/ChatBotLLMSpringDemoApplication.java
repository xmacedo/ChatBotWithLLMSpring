package com.xmacedo;

import com.xmacedo.services.ChatServices;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatBotLLMSpringDemoApplication {

    @Autowired
    ChatServices chatService;

    public static void main(String[] args) {
        SpringApplication.run(ChatBotLLMSpringDemoApplication.class, args);

    }

    @PostConstruct
    public void init() {
        chatService.startChatService();
    }
}