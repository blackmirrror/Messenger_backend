package com.example.messenger.services;

import com.example.messenger.models.Message;
import com.example.messenger.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getMessagesByChatId(Long chatId) {
        return messageRepository.findByChatId(chatId);
    }

    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(Long id, Message message) {
        if (messageRepository.existsById(id)) {
            message.setId(id);
            return messageRepository.save(message);
        }
        return null;
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}

