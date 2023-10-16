package com.example.messenger.repositories;

import com.example.messenger.models.Chat;
import com.example.messenger.models.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findByUserId(Long userId);
    List<ChatMember> findByChatId(Long chatId);
}

