package com.example.consumer;

import com.example.Order;
import com.example.service.RiderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiderReadyConsumer {

    private final ObjectMapper objectMapper;

    private final RiderService riderService;

    @KafkaListener(topics = "order_create", groupId = "rider")
    public void consume(String jsonObject) {
        try {
            Order order = objectMapper.readValue(jsonObject, Order.class);
            riderService.ready(order);
        }catch (Exception e) {

        }
    }

}
