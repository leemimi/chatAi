package org.example.chat_ai.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.dto.ChatRoomListResponse;
import org.example.chat_ai.domain.dto.ChatRoomRequest;
import org.example.chat_ai.domain.dto.ChatRoomResponse;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.example.chat_ai.domain.repository.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom createChatRoom (ChatRoomRequest chatRoomReq) {
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(chatRoomReq.getRoomName())
                .createdAt(LocalDateTime.now())
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    public ChatRoomListResponse getChatRooms () {
        List<ChatRoom> all = chatRoomRepository.findAll();
        ChatRoomListResponse chatRoomListResponse = ChatRoomListResponse.builder()
                .chatRoomList(all.stream()
                        .map(chatRoom -> ChatRoomResponse.builder()
                                .id(chatRoom.getId())
                                .name(chatRoom.getRoomName())
                                .createdAt(chatRoom.getCreatedAt())
                                .build())
                        .toList())
                .build();
        return chatRoomListResponse;
    }

    public ChatRoom getChatRoom (Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }
}
