package com.spring.websocket.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ChatMessage {
    private String content;
    private String sender;

    private MessageType type;
}
