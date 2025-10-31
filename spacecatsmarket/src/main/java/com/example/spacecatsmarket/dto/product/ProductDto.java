package com.example.spacecatsmarket.dto.product;

import com.example.spacecatsmarket.dto.validation.CosmicWordCheck;
import com.example.spacecatsmarket.dto.validation.ExtendedValidation;
import jakarta.validation.GroupSequence;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
@GroupSequence({ ProductDto.class, ExtendedValidation.class })
public class ProductDto {

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    @CosmicWordCheck(groups = ExtendedValidation.class)
    String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    String description;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price cannot be negative")
    Double price;

    @NotNull(message = "Category ID is mandatory")
    Long categoryId;
}
