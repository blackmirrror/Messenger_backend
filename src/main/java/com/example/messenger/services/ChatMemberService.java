package com.example.messenger.services;

import com.example.messenger.models.ChatMember;
import com.example.messenger.repositories.ChatMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMemberService {

    @Autowired
    private ChatMemberRepository chatMemberRepository;

    public ChatMember getChatMemberById(Long id) {
        return chatMemberRepository.findById(id).orElse(null);
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

