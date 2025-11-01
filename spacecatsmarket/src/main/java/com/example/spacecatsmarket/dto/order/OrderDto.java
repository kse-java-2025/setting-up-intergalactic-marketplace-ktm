package com.example.spacecatsmarket.dto.order;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class OrderDto {
    Long id;

    @NotNull(message = "Customer ID is required")
    Long customerId;

    List<OrderItemDto> items;
    Double totalPrice;
    LocalDateTime createdAt;
}
