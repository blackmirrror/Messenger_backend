package com.example.messenger.controller;

import com.example.messenger.models.Chat;
import com.example.messenger.models.ChatMember;
import com.example.messenger.models.User;
import com.example.messenger.services.ChatMemberService;
import com.example.messenger.services.ChatService;
import com.example.messenger.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chatMembers")
public class ChatMemberController {

    @Autowired
    private ChatMemberService chatMemberService;

    @GetMapping
    public ResponseEntity<List<ChatMember>> getAllChatMembers() {
        List<ChatMember> users = chatMemberService.getAllChatMembers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMember> getChatMemberById(@PathVariable Long id) {
        ChatMember chatMember = chatMemberService.getChatMemberById(id);
        if (chatMember != null) {
            return ResponseEntity.ok(chatMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/chats/{id}")
    public ResponseEntity<List<Chat>> getChatsForUser(@PathVariable Long id) {
        List<Chat> chats = chatMemberService.getChatsForUser(id);
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/findCommonChatIds")
    public Long findCommonChatIds(@RequestParam Long userId1, @RequestParam Long userId2) {
        return chatMemberService.findCommonChatId(userId1, userId2);
    }

    @GetMapping("/by-user/{id}")
    public ResponseEntity<List<ChatMember>> getChatMembersByUserId(@PathVariable Long id) {
        List<ChatMember> chatMembers = chatMemberService.getChatMembersByUserId(id);
        return ResponseEntity.ok(chatMembers);
    }

    @GetMapping("/by-chat/{id}")
    public ResponseEntity<List<ChatMember>> getChatMembersByChatId(@PathVariable Long id) {
        List<ChatMember> chatMembers = chatMemberService.getChatMembersByChatId(id);
        return ResponseEntity.ok(chatMembers);
    }

    @PostMapping
    public ResponseEntity<ChatMember> createChatMember(@RequestBody ChatMember chatMember) {
        ChatMember createdChatMember = chatMemberService.createChatMember(chatMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChatMember);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChatMember> updateChatMember(@PathVariable Long id, @RequestBody ChatMember chatMember) {
        ChatMember updatedChatMember = chatMemberService.updateChatMember(id, chatMember);
        if (updatedChatMember != null) {
            return ResponseEntity.ok(updatedChatMember);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChatMember(@PathVariable Long id) {
        chatMemberService.deleteChatMember(id);
        return ResponseEntity.noContent().build();
    }
}

