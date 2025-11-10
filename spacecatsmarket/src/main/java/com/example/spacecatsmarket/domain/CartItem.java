package com.example.spacecatsmarket.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CartItem {
    private Product product;
    private Integer quantity;
}
