package com.example.gpayapp;

public class ChatMessage {
    public String senderId;
    public String receiverId;
    public String message;
    public long timestamp;

    public ChatMessage() {
        // Required for Firebase
    }

    public ChatMessage(String senderId, String receiverId, String message, long timestamp) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
        this.timestamp = timestamp;
    }
}
