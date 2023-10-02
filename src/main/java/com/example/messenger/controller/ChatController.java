package com.example.messenger.controller;

import com.example.messenger.models.Chat;
import com.example.messenger.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long id) {
        Chat chat = chatService.getChatById(id);
        if (chat != null) {
            return ResponseEntity.ok(chat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) {
        Chat createdChat = chatService.createChat(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> updateChat(@PathVariable Long id, @RequestBody Chat chat) {
        Chat updatedChat = chatService.updateChat(id, chat);
        if (updatedChat != null) {
            return ResponseEntity.ok(updatedChat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
        return ResponseEntity.noContent().build();
    }
}

