package org.example.chat_ai.domain.controller;

import lombok.AllArgsConstructor;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.dto.ChatMessageRequest;
import org.example.chat_ai.domain.dto.ChatMessageResponse;
import org.example.chat_ai.domain.dto.ChatRoomRequest;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.example.chat_ai.domain.service.ChatRoomService;
import org.example.chat_ai.domain.service.ChatMessageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/chat")
@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;

    @PostMapping("/rooms")
    public RsData<ChatRoom> makeRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        return RsData.of(
                "200", "채팅방 생성 완료", chatRoomService.createChatRoom(chatRoomRequest));
    }

    @GetMapping("/rooms")
    public RsData<List<ChatRoom>> getRooms() {
        return RsData.of("200", "채팅방 목록 조회 완료", chatRoomService.getChatRooms());
    }

    @GetMapping("/rooms/{roomId}")
    public RsData<ChatRoom> getRoom(@PathVariable Long roomId) {
        return RsData.of("200", "채팅방 조회 완료", chatRoomService.getChatRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/messagesAfter/{afterId}")
    public RsData<List<ChatMessageResponse>> getMessagesAfter(
            @PathVariable("roomId") Long roomId,
            @PathVariable("afterId") Long afterId) {
        return RsData.of("200", "메시지를 조회하였습니다", chatMessageService.getMessagesAfter(roomId, afterId));
    }




}
