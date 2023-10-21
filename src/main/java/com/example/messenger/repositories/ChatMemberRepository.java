package com.example.messenger.repositories;

import com.example.messenger.models.Chat;
import com.example.messenger.models.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findByUserId(Long userId);
    List<ChatMember> findByChatId(Long chatId);
    @Query("SELECT DISTINCT cm.chat.id FROM ChatMember cm " +
            "WHERE (cm.user.id = :userId1 " +
            "OR cm.user.id = :userId2) " +
            "AND cm.chat.id NOT IN (SELECT cm2.chat.id FROM ChatMember cm2 WHERE cm2.user.id != :userId1 AND cm2.user.id != :userId2) ")
    Long findCommonChatId(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

}

