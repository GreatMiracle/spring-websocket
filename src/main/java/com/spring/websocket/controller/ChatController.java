package com.spring.websocket.controller;

import com.spring.websocket.dto.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("chatApp.sendMessage")
    @SendTo("/notification/public")
    public ChatMessage sendMsg (@Payload ChatMessage chatMessage){
        return chatMessage;
    }
}
