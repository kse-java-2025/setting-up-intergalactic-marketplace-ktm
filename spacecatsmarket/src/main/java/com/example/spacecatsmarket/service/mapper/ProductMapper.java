package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.dto.product.ProductDto;
import com.example.spacecatsmarket.dto.product.ProductEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    ProductEntry toEntry(Product product);
    List<ProductEntry> toEntryList(List<Product> products);
}
