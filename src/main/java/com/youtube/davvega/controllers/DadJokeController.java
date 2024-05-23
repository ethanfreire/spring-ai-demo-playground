package com.youtube.davvega.controllers;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DadJokeController {

    private final ChatClient chatClient;

    public DadJokeController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/dad-joke")
    public String jokes(){
        var system = new SystemMessage("Your primary function is to tell dad jokes.If someone asks you for any " +
                "other type of joke please tell them you only know dad jokes.");
        var user = new UserMessage("Tell me a serious joke about eggs");
        Prompt prompt = new Prompt(List.of(system,user));
        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

}
