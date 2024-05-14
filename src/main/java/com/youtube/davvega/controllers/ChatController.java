package com.youtube.davvega.controllers;

import org.springframework.ai.chat.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;



@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/dad-jokes")
    public ResponseEntity<Map<String, String>> generate(@RequestParam(value = "message", defaultValue = "Tell me a dad joke") String message) {
        String joke = chatClient.call(message);
        Map<String, String> response = new HashMap<>();
        response.put("joke", joke);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/3-jokes")
    public String[] threeJokes(@RequestParam(value="message", defaultValue = "Tell me a joke") String message){
        String jokeOne = chatClient.call(message);
        String jokeTwo = chatClient.call(message);
        String jokeThree = chatClient.call(message);

        String[] collectionOfJokes = {jokeOne, jokeTwo, jokeThree};

        return collectionOfJokes;
    }

}
