package org.example.chat_ai.domain.repository;

import org.example.chat_ai.domain.entity.ChatMessage;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByRoomIdAndIdGreaterThan (Long roomId, Long afterId);


    List<ChatMessage> findByRoomId (Long roomId);
}
