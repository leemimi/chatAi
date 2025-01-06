package org.example.chat_ai.domain.controller;

import lombok.AllArgsConstructor;
import org.example.chat_ai.base.RsData;
import org.example.chat_ai.domain.dto.ChatRequest;
import org.example.chat_ai.domain.dto.ChatResponse;
import org.example.chat_ai.domain.dto.ChatRoomRequest;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.example.chat_ai.domain.service.ChatRoomService;
import org.example.chat_ai.domain.service.ChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/chat")
@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatRoomService chatRoomService;
    private final ChatService chatService;

    @PostMapping("/rooms")
    @ResponseBody
    public RsData<ChatRoom> makeRoom(@RequestBody ChatRoomRequest chatRoomRequest) {
        return RsData.of(
                "200", "채팅방 생성 완료", chatRoomService.createChatRoom(chatRoomRequest));
    }

    @GetMapping("/rooms")
    @ResponseBody
    public RsData<List<ChatRoom>> getRooms() {
        return RsData.of("200", "채팅방 목록 조회 완료", chatRoomService.getChatRooms());
    }

    @GetMapping("/rooms/{roomId}")
    @ResponseBody
    public RsData<ChatRoom> getRoom(@PathVariable Long roomId) {
        return RsData.of("200", "채팅방 조회 완료", chatRoomService.getChatRoom(roomId));
    }

    @PostMapping("/rooms/{roomId}/messages")
    @ResponseBody
    public RsData<String> sendMessage(@PathVariable Long roomId, @RequestBody ChatRequest chatRequest) {
        return RsData.of("200", "메시지 전송 완료", chatService.sendMessage(roomId, chatRequest));
    }

    @GetMapping("/rooms/{roomId}/messages")
    @ResponseBody
    public RsData<List<ChatResponse>> getMessagesAfter(
            @PathVariable("roomId") Long roomId,
            @RequestParam("afterChatMessageId") Long afterChatMessageId) {
        return RsData.of("200", "메시지를 조회하였습니다", chatService.getMessagesAfter(roomId, afterChatMessageId));
    }

}
