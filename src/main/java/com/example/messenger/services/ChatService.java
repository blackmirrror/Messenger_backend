package com.example.messenger.services;

import com.example.messenger.models.Chat;
import com.example.messenger.repositories.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(Long id) {
        return chatRepository.findById(id).orElse(null);
    }

    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    public Chat updateChat(Long id, Chat chat) {
        if (chatRepository.existsById(id)) {
            chat.setId(id);
            return chatRepository.save(chat);
        }
        return null;
    }

    public void deleteChat(Long id) {
        chatRepository.deleteById(id);
    }
}

