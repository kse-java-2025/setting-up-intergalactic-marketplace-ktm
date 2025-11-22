package com.example.spacecatsmarket.domain;

import lombok.*;

@Data
@Builder
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long categoryId;

     public Product(Long id, String name, String description, Double price, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
    }
}
