package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Order {
    private Long OrderId;
    private Long storeId;
    private Integer priceId;
    private Long userId;
}
