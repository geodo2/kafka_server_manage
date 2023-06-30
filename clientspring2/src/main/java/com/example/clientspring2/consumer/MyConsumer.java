package com.example.clientspring2.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Component
public class MyConsumer {
    @KafkaListener(topics = "my-topic",groupId = "test")
    public void receiveMessage(String message) {
        System.out.println("Received message: " + message);
        // 메시지 처리 로직 작성
    }
}