package com.example.messenger.controller;

import com.example.messenger.models.Chat;
import com.example.messenger.models.Message;
import com.example.messenger.models.User;
import com.example.messenger.services.ChatService;
import com.example.messenger.services.MessageService;
import com.example.messenger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        Message message = messageService.getMessageById(id);
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long chatId) {
        List<Message> messages = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        User sender = userService.getUserById(message.getSender().getId());
        Chat chat = chatService.getChatById(message.getChat().getId());
        message.setSender(sender);
        message.setChat(chat);
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        Message updatedMessage = messageService.updateMessage(id, message);
        if (updatedMessage != null) {
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}

