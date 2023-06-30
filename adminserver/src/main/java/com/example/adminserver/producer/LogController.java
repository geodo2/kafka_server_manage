package com.example.adminserver.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/logs")
public class LogController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public LogController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    @ResponseBody
    public String sendLog() {

        String logMessage = "This is a log message."; // 전송할 로그 메시지
        kafkaTemplate.send("my-topic", logMessage);
        return "Log sent: " + logMessage;
    }
}

