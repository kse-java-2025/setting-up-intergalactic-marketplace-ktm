package com.example.spacecatsmarket.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long customerId;
    private List<Product> products;
    private Double totalPrice;
    private LocalDateTime orderDate;
}
