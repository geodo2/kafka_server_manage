package com.example.producer;

import com.example.Order;
import com.example.OrderState;
import com.example.domain.Payment;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentCompleteProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(Order order) {
        try {
            OrderState message = new OrderState(order.getOrderId(), order.getStoreId(), order.getUserId(), "payment-complete");
            String jsonObject = objectMapper.writeValueAsString(message);
            kafkaTemplate.send("order_state", jsonObject);
        } catch (Exception e) {

        }
    }
}
