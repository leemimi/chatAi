package org.example.chat_ai.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponse {
    private Long roomId;
    private String message; // 질문
    private String writerName; // 작성자
    private String createdAt; // 작성시간
    private boolean isMyMessage;
}
