package com.example.consumer;

import com.example.Order;
import com.example.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOrderCreatedConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @KafkaListener(topics = {"order_create"}, groupId = "payment")
    public void consume(String jsonObject) {
        try {
            var order = objectMapper.readValue(jsonObject, Order.class);
            paymentService.pay(order);
        } catch (Exception e){
            // 실패에 대한 로직 추가
            // 이걸 발송한 사람한테 다시 요청
        }
    }
}
