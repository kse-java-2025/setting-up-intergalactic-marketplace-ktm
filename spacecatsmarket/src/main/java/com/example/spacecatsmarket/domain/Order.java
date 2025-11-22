package com.example.spacecatsmarket.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Order {
    private Long id;
    private Long customerId;
    private List<OrderItem> products;
    private Double totalPrice;
    private LocalDateTime orderDate;

    public Order(Long id, Long customerId, List<OrderItem> products, Double totalPrice, LocalDateTime orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
