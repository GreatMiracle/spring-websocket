package com.spring.websocket.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketEventListener {

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event){

    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event){

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