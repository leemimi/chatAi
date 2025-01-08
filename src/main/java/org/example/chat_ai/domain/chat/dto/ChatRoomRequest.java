package org.example.chat_ai.domain.chat.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class ChatRoomRequest {
    private String roomName;
}
