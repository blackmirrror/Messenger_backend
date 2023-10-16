package com.example.messenger.services;

import com.example.messenger.models.Chat;
import com.example.messenger.models.ChatMember;
import com.example.messenger.repositories.ChatMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMemberService {

    @Autowired
    private ChatMemberRepository chatMemberRepository;

    public ChatMember getChatMemberById(Long id) {
        return chatMemberRepository.findById(id).orElse(null);
    }

    public List<ChatMember> getAllChatMembers() { return chatMemberRepository.findAll();}

    public List<Chat> getChatsForUser(Long userId) {
        List<ChatMember> chatMembers = chatMemberRepository.findByUserId(userId);
        List<Chat> chats = chatMembers.stream()
                .map(ChatMember::getChat)
                .collect(Collectors.toList());
        return chats;
    }

    public List<ChatMember> getChatMembersByUserId(Long userId) {
        return chatMemberRepository.findByUserId(userId);
    }

    public List<ChatMember> getChatMembersByChatId(Long chatId) {
        return chatMemberRepository.findByChatId(chatId);
    }

    public ChatMember createChatMember(ChatMember chatMember) {
        return chatMemberRepository.save(chatMember);
    }

    public ChatMember updateChatMember(Long id, ChatMember chatMember) {
        if (chatMemberRepository.existsById(id)) {
            chatMember.setId(id);
            return chatMemberRepository.save(chatMember);
        }
        return null;
    }

    public void deleteChatMember(Long id) {
        chatMemberRepository.deleteById(id);
    }
}

