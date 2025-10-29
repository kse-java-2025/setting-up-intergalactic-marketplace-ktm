package com.example.spacecatsmarket.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderItemDto {
    @NotNull(message = "Product ID is required")
    Long productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    Integer quantity;

    @Min(value = 0, message = "Price cannot be negative")
    Double price;
}
