package com.example.spacecatsmarket.dto.category;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class CategoryListDto {
    List<CategoryEntry> categories;
}
