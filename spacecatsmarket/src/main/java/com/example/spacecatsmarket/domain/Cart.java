package com.example.spacecatsmarket.domain;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Long id;
    private Long customerId;
    private List<Product> items;
    private Double totalAmount;
}
