package com.example.schedule;

import com.example.domain.FailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@EnableScheduling
@RequiredArgsConstructor
public class FailEventSchedule {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final FailRepository failRepository;

    @Scheduled(fixedDelay = 1000)
    public void scheduleQueue() {
        failRepository.findAllByComplete(false)
                .forEach(failEvent -> {
                    kafkaTemplate.send("order_create", failEvent.getJsonObject());
                    failEvent.Complete();
                });
    }
}
