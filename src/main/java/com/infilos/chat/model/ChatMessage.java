package com.infilos.chat.model;

import lombok.Data;

@Data
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}
