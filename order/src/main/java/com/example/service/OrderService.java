package com.example.service;

import com.example.Order;
import com.example.domain.*;
import com.example.dto.OrderRequest;
import com.example.producer.OrderCreatedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;

    private final OrderCreatedProducer orderCreatedProducer;

    public void addOrder(OrderRequest request) {
        // 오더 도메인 생성
        Orders orders = new Orders(null, request.getUserId(), request.getStoreId(), OrderState.CREATED, request.getPrice());

        orderRepository.save(orders);

        orderHistoryRepository.save(new OrderHistory(orders));

        orderCreatedProducer.send(orders);
    }


    public void state(com.example.OrderState orderState) {
        Long orderId = orderState.getOrderId();
        Orders orders = orderRepository.findById(orderId).orElseThrow();
        switch (orderState.getState()) {
            case "payment-complete":
                orders.setOrderState(OrderState.PAYMENT);
                orderHistoryRepository.save(new OrderHistory(orders));
                break;
            case "rider-ready":
                orderHistoryRepository.save(new OrderHistory(new Orders(
                        orders.getId(), orders.getUserId(), orders.getStoreId(), OrderState.RIDER_READY, orders.getPrice()
                )));
                break;
            case "pay-fail":
                orders.setOrderState(OrderState.CANCEL);
                orderHistoryRepository.save(new OrderHistory(orders));
                break;
        }

    }
}
