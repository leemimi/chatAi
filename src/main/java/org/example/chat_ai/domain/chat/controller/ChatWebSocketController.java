package org.example.chat_ai.domain.chat.controller;


import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.chat.dto.ChatMessageRequest;
import org.example.chat_ai.domain.chat.dto.ChatMessageResponse;
import org.example.chat_ai.domain.chat.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final ChatMessageService chatMessageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/{roomId}")

    public void sendMessage(@DestinationVariable Long roomId, ChatMessageRequest chatMessageRequest) {
        ChatMessageResponse response = ChatMessageResponse.builder()
                .roomId(roomId)
                .writerName(chatMessageRequest.getWriterName())
                .message(chatMessageRequest.getMessage())
                .build();

        // /topic/room.{roomId} 형식으로 메시지 전송
        messagingTemplate.convertAndSend("/topic/room/" + roomId, response);
    }
}
