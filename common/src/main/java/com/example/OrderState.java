package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderState {
    private Long OrderId;
    private Long storeId;
    private Long userId;
    private String state;
}
