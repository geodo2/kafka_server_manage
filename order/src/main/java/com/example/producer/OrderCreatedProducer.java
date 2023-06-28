package com.example.producer;

import com.example.Order;
import com.example.domain.FailEvent;
import com.example.domain.FailRepository;
import com.example.domain.Orders;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderCreatedProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final FailRepository failRepository;

    public void send(Orders orders) {
        String jsonObject = null;
        try{
            jsonObject = objectMapper.writeValueAsString(new Order(orders.getId(), orders.getStoreId(), orders.getPrice(), orders.getUserId()));

            if(orders.getUserId() == 2L) throw new Exception();

            kafkaTemplate.send("order_create", jsonObject);
        }catch (Exception e) {
            // TODO: Exception 이 발생한 경우 재처리 로직 추가
            FailEvent of = FailEvent.of(jsonObject);
            failRepository.save(of);
        }
    }
}
