package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Category;
import com.example.spacecatsmarket.dto.category.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
