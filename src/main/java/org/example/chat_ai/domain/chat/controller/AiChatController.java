package org.example.chat_ai.domain.chat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai_chat")
@AllArgsConstructor
public class AiChatController {
    //룸은 이따가 수정하기
//    private final ChatMessageService chatMessageService;
//    @PostMapping("/rooms/{roomId}/messages")
//    @ResponseBody
//    public RsData<String> sendMessage(@PathVariable Long roomId, @RequestBody ChatMessageRequest chatMessageRequest) {
//        return RsData.of("200", "메시지 전송 완료", chatMessageService.sendAiMessage(roomId, chatMessageRequest));
//    }

//    @GetMapping("/rooms/{roomId}/messages")
//    @ResponseBody
//    public RsData<List<ChatMessageResponse>> getMessagesAfter(
//            @PathVariable("roomId") Long roomId,
//            @RequestParam("afterChatMessageId") Long afterChatMessageId) {
//        return RsData.of("200", "메시지를 조회하였습니다", chatMessageService.getAiMessagesAfter(roomId, afterChatMessageId));
//    }
}
