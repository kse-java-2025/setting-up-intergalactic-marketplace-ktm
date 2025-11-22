package com.example.spacecatsmarket.domain;
import lombok.*;

@Data
@Builder
public class OrderItem {
    private Product product;
    private Integer quantity;
    private Double price;
}
