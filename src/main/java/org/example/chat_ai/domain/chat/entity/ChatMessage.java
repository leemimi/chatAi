package org.example.chat_ai.domain.chat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.chat_ai.base.BaseEntity;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "chat")
public class ChatMessage extends BaseEntity {

    @Column(nullable = false)
    private String writerName; // 작성자 이름

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

    private boolean isMyMessage;
}
