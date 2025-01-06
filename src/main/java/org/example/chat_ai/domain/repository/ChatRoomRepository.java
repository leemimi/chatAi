package org.example.chat_ai.domain.repository;

import org.example.chat_ai.domain.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
