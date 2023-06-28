package com.example.clientspring1.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
}
