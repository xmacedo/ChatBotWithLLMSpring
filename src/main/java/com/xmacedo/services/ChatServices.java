package com.xmacedo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class ChatServices {

    @Autowired
    ChatbotServices chatbot;

    final Logger log1 = LoggerFactory.getLogger(this.getClass());

    public void startChatService() {

        Scanner scanner = new Scanner(System.in);
        //Welcome Greetings
        StringBuilder welcomeGreetings = new StringBuilder();
        welcomeGreetings.append(System.lineSeparator() + System.lineSeparator());
        welcomeGreetings.append("Welcome to LLM Chatbot Apps" + System.lineSeparator());
        welcomeGreetings.append("Please insert your question after the > prompt" + System.lineSeparator());
        welcomeGreetings.append("Type 'exit' to leave the chat prompt " + System.lineSeparator());

        System.out.print(welcomeGreetings);
        while (true) {

            System.out.print(System.lineSeparator() + "user (it's you)> ");
            try {
                String input = scanner.nextLine();
                if (input.length() < 2) {
                    System.out.println(System.lineSeparator());
                } else if (input.equalsIgnoreCase("exit")) {
                    break;
                } else {
                    chatbot.generateResponse(input);
                }
            } catch (Exception e) {
                log1.error("", e);
            }
        }

        //Close scanner
        scanner.close();

        //Close the app
        System.exit(0);
    }
}
