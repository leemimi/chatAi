package org.example.chat_ai.domain.repository;

import org.example.chat_ai.domain.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByRoomIdAndIdGreaterThan (Long roomId, Long afterId);
}
