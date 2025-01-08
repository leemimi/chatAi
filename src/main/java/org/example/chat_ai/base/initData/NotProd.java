package org.example.chat_ai.base.initData;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.example.chat_ai.domain.chat.entity.ChatRoom;
import org.example.chat_ai.domain.chat.service.ChatMessageService;
import org.example.chat_ai.domain.chat.service.ChatRoomService;
import org.example.chat_ai.domain.member.entity.Member;
import org.example.chat_ai.domain.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner applicationRunner(ChatRoomService chatRoomService, ChatMessageService chatMessageService, MemberService memberService) {
        return args -> {
//            ChatRoom chatRoom1 = chatRoomService.createChatRoom("room1");
//            ChatRoom chatRoom2 = chatRoomService.createChatRoom("room2");
//            ChatRoom chatRoom3 = chatRoomService.createChatRoom("room3");

//            IntStream.rangeClosed(1, 100).forEach(num -> {
//                chatMessageService.create(chatRoom1, "홍길동", "채팅메세지" + num);
//            });
            Member member1 = memberService.join("user1", "1234").getData();
            Member member2 = memberService.join("user2", "1234").getData();
            Member member3 = memberService.join("user3", "1234").getData();
        };
    }
}
