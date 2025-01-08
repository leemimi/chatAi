package org.example.chat_ai.domain.chat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ChatMessageRequest {
    private String message;
    private String writerName;
    private String createdAt; // 작성시간
    private boolean isMyMessage;

    public boolean getIsMyMessage() {
        return isMyMessage;
    }
}
