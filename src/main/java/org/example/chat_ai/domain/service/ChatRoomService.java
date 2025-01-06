package org.example.chat_ai.domain.service;

import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.dto.ChatRoomRequest;
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
                .createDate(LocalDateTime.now())
                .modifyDate(null)
                .build();
        return chatRoomRepository.save(chatRoom);
    }

    public List<ChatRoom> getChatRooms () {
        return chatRoomRepository.findAll();
    }

    public ChatRoom getChatRoom (Long roomId) {
        return chatRoomRepository.findById(roomId).orElseThrow();
    }
}
