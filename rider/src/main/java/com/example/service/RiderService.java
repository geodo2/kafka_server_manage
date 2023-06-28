package com.example.service;

import com.example.Order;
import com.example.domain.RiderRepository;
import com.example.producer.RiderReadyCompleteProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;
    private final RiderReadyCompleteProducer riderReadyCompleteProducer;

    public void ready(Order order) throws InterruptedException {
        Thread.sleep(3000);
        // 내부적인 라이더를 지정하는 알고리즘이 돈다.

        riderReadyCompleteProducer.send(order);


    }
}
