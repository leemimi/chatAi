package org.example.chat_ai.domain.chat.controller;

import lombok.AllArgsConstructor;
import org.example.chat_ai.global.base.RsData;
import org.example.chat_ai.domain.chat.dto.ChatMessageRequest;
import org.example.chat_ai.domain.chat.dto.ChatMessageResponse;
import org.example.chat_ai.domain.chat.dto.ChatRoomListResponse;
import org.example.chat_ai.domain.chat.dto.ChatRoomRequest;
import org.example.chat_ai.domain.chat.entity.ChatRoom;
import org.example.chat_ai.domain.chat.service.ChatRoomService;
import org.example.chat_ai.domain.chat.service.ChatMessageService;
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
    public RsData<ChatRoomListResponse> getRooms() {
        return RsData.of("200", "채팅방 목록 조회 완료", chatRoomService.getChatRooms());
    }

    @GetMapping("/rooms/{roomId}/messages")
    public RsData<List<ChatMessageResponse>> getRoomMessages(@PathVariable Long roomId) {
        List<ChatMessageResponse> messages = chatMessageService.getMessages(roomId);
        return RsData.of("200", "메시지 목록 조회 완료", messages);
    }
    @PostMapping("/rooms/{roomId}/messages")
    public RsData<Void> postMessage(@PathVariable Long roomId, @RequestBody ChatMessageRequest request) {
        // 메시지 저장
        chatMessageService.saveMessage(roomId, request);
        return RsData.of("200", "메시지 저장 완료",null);
    }


    @PostMapping("/rooms/{roomId}/messagesAfter/{afterId}")
    public RsData<List<ChatMessageResponse>> getMessagesAfter(
            @PathVariable("roomId") Long roomId,
            @PathVariable("afterId") Long afterId) {
        return RsData.of("200", "메시지를 조회하였습니다", chatMessageService.getMessagesAfter(roomId, afterId));
    }




}
