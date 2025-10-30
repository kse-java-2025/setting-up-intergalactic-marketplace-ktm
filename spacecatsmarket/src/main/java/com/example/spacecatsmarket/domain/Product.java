package com.example.spacecatsmarket.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
