package org.example.chat_ai.domain.entity;

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
public class Chat extends BaseEntity {

    @Column(nullable = false)
    private String writerName; // 작성자 이름

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(length = 1000)
    private String aiResponse;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

}
