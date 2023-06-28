package com.example.controller;

import com.example.dto.OrderRequest;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderRequest orderRequest) {
        orderService.addOrder(orderRequest);
        return ResponseEntity.ok().build();
    }
}
