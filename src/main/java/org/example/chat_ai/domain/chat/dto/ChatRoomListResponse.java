package org.example.chat_ai.domain.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoomListResponse {
    List<ChatRoomResponse> chatRoomList;
}
