package com.example.service;

import com.example.Order;
import com.example.domain.Payment;
import com.example.domain.PaymentRepository;
import com.example.producer.PaymentCompleteProducer;
import com.example.producer.PaymentFailProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentCompleteProducer paymentCompleteProducer;

    private final PaymentFailProducer paymentFailProducer;

    public void pay(Order order) throws InterruptedException {

        try{
            if(order.getUserId() == 1L){    //테스트용 예외 던지기
                throw new Exception();
            }
            Payment payment = new Payment(null, order.getStoreId(), order.getOrderId(), order.getPriceId());
            Thread.sleep(5000);
            paymentRepository.save(payment);
            // 결제 완료 이벤트 발송로직을 추가해야한다.
            // 성공완료 이벤트 로직은 스프링 이벤트로 분리해야한다.
            paymentCompleteProducer.send(order);
        } catch (Exception e) {
            paymentFailProducer.send(order);
        }

    }
}
