package com.example.clientspring2.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableScheduling

public class MyController {
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public MyController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @RequestMapping("publish")
    public void publishMessage() {
        System.out.println("dsds");
        String topic = "my-topic";
        String message = "Hello, Kafka!";
        kafkaTemplate.send(topic, message);
    }
    @Scheduled(fixedDelay = 1000) // 0.5초마다 실행
    public void sendMessage() {
        String message = "0.5 seconds!"; // 전송할 메시지
        kafkaTemplate.send("my-topic", message);
        System.out.println("Sent message: " + message);
    }
}
