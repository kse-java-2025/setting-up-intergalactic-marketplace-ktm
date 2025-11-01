package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Product;
import com.example.spacecatsmarket.dto.product.ProductDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void testToDto() {
        // given: a domain object
        Product product = Product.builder()
                .name("Galaxy Milkshake")
                .description("Sweet intergalactic treat")
                .price(9.99)
                .categoryId(42L)
                .build();

        // when: we map it to DTO
        ProductDto dto = mapper.toDto(product);

        // then: the fields must match
        assertEquals(product.getName(), dto.getName());
        assertEquals(product.getDescription(), dto.getDescription());
        assertEquals(product.getPrice(), dto.getPrice());
        assertEquals(product.getCategoryId(), dto.getCategoryId());
    }

    @Test
    void testToEntity() {
        // given: a DTO
        ProductDto dto = ProductDto.builder()
                .name("Galaxy Burger")
                .description("Made from cosmic beef")
                .price(12.50)
                .categoryId(100L)
                .build();

        // when: we map it to domain entity
        Product entity = mapper.toEntity(dto);

        // then: the fields must match
        assertEquals(dto.getName(), entity.getName());
        assertEquals(dto.getDescription(), entity.getDescription());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getCategoryId(), entity.getCategoryId());
    }
}
