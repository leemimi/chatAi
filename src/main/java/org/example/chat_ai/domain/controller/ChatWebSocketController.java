package org.example.chat_ai.domain.controller;


import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.dto.ChatMessageRequest;
import org.example.chat_ai.domain.dto.ChatMessageResponse;
import org.example.chat_ai.domain.service.ChatMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat/{roomId}") // 클라이언트에서 메시지 전송 경로
    @SendTo("/topic/chat/{roomId}") // 해당 방에 메시지 브로드캐스트
    public ChatMessageResponse sendMessage(@PathVariable Long roomId, ChatMessageRequest chatMessageRequest) {
        // 메시지 저장
        chatMessageService.saveMessage(roomId, chatMessageRequest);

        // 메시지를 반환하여 클라이언트로 브로드캐스트
        return ChatMessageResponse.builder()
                .roomId(roomId)
                .writerName(chatMessageRequest.getWriterName())
                .message(chatMessageRequest.getMessage())
                .build();
    }
}
