package com.example.spacecatsmarket.dto.product;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class ProductEntry {
    Long id;
    String name;
    String description;
    Double price;
    Long categoryId;
}
