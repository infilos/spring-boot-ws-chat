package com.infilos.chat.controller;

import com.infilos.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {

    @MessageMapping("/chat.register")   // 接收客户端发向 "/app/chat.register" 的消息
    @SendTo("/topic/public")            // 将接收到的消息广播到 "/topic/public" 
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        if (Objects.nonNull(headerAccessor.getSessionAttributes())) {
            headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        }

        return chatMessage;
    }

    @MessageMapping("/chat.send")   // 接收客户端发向 "/app/chat.send" 的消息
    @SendTo("/topic/public")        // 将接收到的消息广播到 "/topic/public"
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }
}
