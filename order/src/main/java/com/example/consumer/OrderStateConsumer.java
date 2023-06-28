package com.example.consumer;

import com.example.Order;
import com.example.OrderState;
import com.example.domain.OrderHistoryRepository;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderStateConsumer {

    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order_state", groupId = "order")
    public void consume(String jsonObject) {
        try {
            var orderState = objectMapper.readValue(jsonObject, OrderState.class);
            orderService.state(orderState);
        }catch (Exception e) {

        }
    }
}
