package org.example.chat_ai.base;


import org.example.chat_ai.domain.dto.ChatRoomRequest;
import org.example.chat_ai.domain.entity.ChatRoom;
import org.example.chat_ai.domain.service.ChatRoomService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.InputStream;

@Configuration
@Profile("!prod")
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(ChatRoomService chatRoomService) {
        return args -> {
//            ChatRoomRequest chatRoomRequest = new ChatRoomRequest();
//            chatRoomRequest.setRoomName("공부");
//            ChatRoom chatRoom1 = chatRoomService.createChatRoom(chatRoomRequest);
//            chatRoomRequest.setRoomName("20대 채팅방");
//
//            ChatRoom chatRoom2 = chatRoomService.createChatRoom(chatRoomRequest);
//            chatRoomRequest.setRoomName("50대 채팅방");
//            ChatRoom chatRoom3 = chatRoomService.createChatRoom(chatRoomRequest);
////            IntStream.rangeClosed(1, 100).forEach(num -> {
////            });


            System.out.println("Not Prod");
        };
    }
}
