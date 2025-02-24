package org.example.chat_ai.domain.chat.service;

import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.chat.dto.ChatMessageRequest;
import org.example.chat_ai.domain.chat.dto.ChatMessageResponse;
import org.example.chat_ai.domain.chat.entity.ChatMessage;
import org.example.chat_ai.domain.chat.entity.ChatRoom;
import org.example.chat_ai.domain.chat.repository.ChatMessageRepository;
import org.example.chat_ai.domain.chat.repository.ChatRoomRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
//    private final OpenAiChatModel openAiChatModel;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

//    public String sendAiMessage (Long roomId, ChatMessageRequest chatMessageRequest) {
//        String response = openAiChatModel.call(chatMessageRequest.getMessage());
//
//        ChatMessage chatMessage = ChatMessage.builder()
//                .room(getChatRoom(roomId))
//                .message(chatMessageRequest.getMessage())
//                .writerName(chatMessageRequest.getWriterName())
//                .createdAt(LocalDateTime.now())
//                .build();
//
//        chatMessageRepository.save(chatMessage);
//        return response;
//    }

    private ChatRoom getChatRoom (Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }


    // 메시지 저장
    public void saveMessage(Long roomId, ChatMessageRequest request) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        ChatMessage chatMessage = ChatMessage.builder()
                .room(chatRoom)
                .writerName(request.getWriterName())
                .message(request.getMessage())
                .createdAt(LocalDateTime.now())
                .isMyMessage(request.getIsMyMessage())
                .build();
        chatMessageRepository.save(chatMessage);
    }

    // afterId 이후의 메시지 조회
    public List<ChatMessageResponse> getMessagesAfter(Long roomId, Long afterId) {
        return chatMessageRepository.findByRoomIdAndIdGreaterThan(roomId, afterId)
                .stream()
                .map(chatMessage -> ChatMessageResponse.builder()
                        .roomId(chatMessage.getRoom().getId())
                        .writerName(chatMessage.getWriterName())
                        .message(chatMessage.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

    public List<ChatMessageResponse> getMessages (Long roomId) {
        return chatMessageRepository.findByRoomId(roomId)
                .stream()
                .map(chatMessage -> ChatMessageResponse.builder()
                        .roomId(chatMessage.getRoom().getId())
                        .writerName(chatMessage.getWriterName())
                        .message(chatMessage.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

//    public void saveMessage (Long roomId, String writerName, String message) {
//        ChatRoom chatRoom = chatRoomRepository.findById(roomId)
//                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));
//
//        ChatMessage chatMessage = ChatMessage.builder()
//                .room(chatRoom)
//                .writerName(writerName)
//                .message(message)
//                .createdAt(LocalDateTime.now())
//                .isMyMessage()
//                .build();
//        chatMessageRepository.save(chatMessage);
//    }
}
