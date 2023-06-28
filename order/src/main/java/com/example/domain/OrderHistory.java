package com.example.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "orders_history")
@Entity
@Getter
@NoArgsConstructor
public class OrderHistory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderState orderState;

    private Long orderId;

    public OrderHistory(Orders orders) {
        this.id = null;
        this.orderState = orders.getOrderState();
        this.orderId = orders.getId();
    }
}