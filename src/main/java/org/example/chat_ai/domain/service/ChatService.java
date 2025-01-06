package org.example.chat_ai.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.dto.ChatRequest;
import org.example.chat_ai.domain.dto.ChatResponse;
import org.example.chat_ai.domain.entity.Chat;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.example.chat_ai.domain.repository.ChatRepository;
import org.example.chat_ai.domain.repository.ChatRoomRepository;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final OpenAiChatModel openAiChatModel;
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;

    public String sendMessage (Long roomId, ChatRequest chatRequest) {
        String response = openAiChatModel.call(chatRequest.getMessage());

        Chat chat = Chat.builder()
                .room(getChatRoom(roomId))
                .message(chatRequest.getMessage())
                .writerName(chatRequest.getWriterName())
                .aiResponse(response)
                .createDate(LocalDateTime.now())
                .build();

        chatRepository.save(chat);
        return response;
    }


    private ChatRoom getChatRoom (Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }

    public List<ChatResponse> getMessagesAfter (Long roomId, Long afterChatMessageId) {
        return (List<ChatResponse>) chatRepository.findByRoomIdAndIdGreaterThan(roomId, afterChatMessageId)
            .stream()
            .map(chat -> Chat.builder()
                    .room(getChatRoom(roomId))
                    .message(chat.getMessage())
                    .writerName(chat.getWriterName())
                    .aiResponse(chat.getAiResponse())
                    .build())
            .collect(Collectors.toList()).reversed();
        }
}
