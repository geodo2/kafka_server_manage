package com.example.dto;

import lombok.Data;

@Data
public class OrderRequest {

    private Long storeId;
    private Long userId;
    private Integer price;
}
