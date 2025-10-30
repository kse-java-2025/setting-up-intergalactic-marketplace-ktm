package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Cart;
import com.example.spacecatsmarket.dto.cart.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto toDto(Cart cart);
    Cart toEntity(CartDto cartDto);
}
