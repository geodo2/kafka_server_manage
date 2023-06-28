package com.example.producer;

import com.example.Order;
import com.example.OrderState;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RiderReadyCompleteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(Order order) {
        try {
            OrderState message = new OrderState(order.getOrderId(), order.getStoreId(), order.getUserId(), "rider-ready");
            String jsonObject = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("order_state", jsonObject);
        } catch (Exception e) {

        }
    }
}
