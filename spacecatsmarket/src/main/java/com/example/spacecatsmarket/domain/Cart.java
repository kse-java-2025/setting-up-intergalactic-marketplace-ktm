package com.example.spacecatsmarket.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
public class Cart {
    private Long id;
    private Long customerId;
    private List<Product> items;
    private Double totalAmount;
    
    public Cart(Long id, Long customerId, List<Product> items, Double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.totalAmount = totalAmount;
    }
}
