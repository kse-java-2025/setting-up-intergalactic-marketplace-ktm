package com.example.spacecatsmarket.dto.cart;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CartDto {
    @NotNull(message = "Customer ID is required")
    Long customerId;

    List<CartItemDto> items;
}
