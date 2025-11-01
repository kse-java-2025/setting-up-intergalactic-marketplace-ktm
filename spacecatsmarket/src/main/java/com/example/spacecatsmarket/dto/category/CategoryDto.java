package com.example.spacecatsmarket.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CategoryDto {

    @NotBlank(message = "Category name is mandatory")
    @Size(max = 100, message = "Category name cannot exceed 100 characters")
    String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    String description;
}
