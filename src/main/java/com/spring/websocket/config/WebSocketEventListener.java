package com.spring.websocket.config;

import com.spring.websocket.dto.ChatMessage;
import com.spring.websocket.dto.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
        String userName = (String) headerAccessor.getSessionAttributes().get("userName");
        if(StringUtils.hasText(userName)){

            log.info("User disconnected: " + userName + " with sessionId: " + sessionId);
            ChatMessage chatMessage = ChatMessage.builder()
                    .sender(userName)
                    .type(MessageType.LEAVE)
                    .build();

            messagingTemplate.convertAndSend("/notification/public", chatMessage);
        }
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String sessionId = headerAccessor.getSessionId();
        String userName = (String) headerAccessor.getHeader("simpUserName");

        System.out.println("User connected: " + userName + " with sessionId: " + sessionId);
    }

}


//@Component
//public class WebSocketConnectListener implements ApplicationListener<SessionConnectEvent> {
//
//    @Override
//    public void onApplicationEvent(SessionConnectEvent event) {
//        // Xử lý sự kiện kết nối WebSocket
//        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headerAccessor.getSessionId();
//        String userName = (String) headerAccessor.getHeader("simpUserName");
//
//        System.out.println("User connected: " + userName + " with sessionId: " + sessionId);
//    }
//}
//
//@Component
//public class WebSocketDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {
//
//    @Override
//    public void onApplicationEvent(SessionDisconnectEvent event) {
//        // Xử lý sự kiện ngắt kết nối WebSocket
//        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        String sessionId = headerAccessor.getSessionId();
//        String userName = (String) headerAccessor.getHeader("simpUserName");
//
//        System.out.println("User disconnected: " + userName + " with sessionId: " + sessionId);
//    }
//}