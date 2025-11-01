package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Category;
import com.example.spacecatsmarket.dto.category.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
}
