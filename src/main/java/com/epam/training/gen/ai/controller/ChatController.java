package com.epam.training.gen.ai.controller;


import com.epam.training.gen.ai.service.ChatService;
import com.microsoft.semantickernel.services.chatcompletion.ChatHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/chat")
@RequiredArgsConstructor
@RestController
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping("/ask")
    public ResponseEntity<List<Object>> getChatResponse(@RequestBody Map<String, String> request) {

        // Validate request input
        if (!request.containsKey("input") || request.get("input").trim().isEmpty()) {
            return ResponseEntity.badRequest().body(List.of("Input cannot be empty"));
        }
        String input = request.get("input");

        // Generate AI response
        ChatHistory chatHistory = chatService.processWithHistory(input);

        List<Object> result = new ArrayList<>();
        result.addAll(chatHistory.getMessages().stream().map(message -> Map.of(
                "type", message.getAuthorRole(),
                "contents", message.getContent()
        )).toList());

        return ResponseEntity.ok(result);
    }
}