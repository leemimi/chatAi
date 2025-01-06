package org.example.chat_ai.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ChatMessageRequest {
    private String message;
    private String writerName;
}
