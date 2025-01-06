package org.example.chat_ai.domain.repository;

import org.example.chat_ai.domain.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdAndIdGreaterThan (Long roomId, Long afterId);
}
