package com.example.spacecatsmarket.dto.product;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class ProductListDto {
    List<ProductEntry> products;
}
