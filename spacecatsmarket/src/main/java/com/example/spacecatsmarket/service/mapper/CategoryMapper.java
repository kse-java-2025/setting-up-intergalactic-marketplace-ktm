package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Category;
import com.example.spacecatsmarket.dto.category.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true) // service generates ID
    Category toEntity(CategoryDto categoryDto);
}
