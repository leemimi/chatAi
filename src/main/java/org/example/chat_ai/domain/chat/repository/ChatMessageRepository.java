package org.example.chat_ai.domain.chat.repository;

import org.example.chat_ai.domain.chat.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdAndIdGreaterThan (Long roomId, Long afterId);


    List<ChatMessage> findByRoomId (Long roomId);
}
