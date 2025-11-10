package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Cart;
import com.example.spacecatsmarket.dto.cart.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { CartItemMapper.class })
public interface CartMapper {
    CartDto toDto(Cart cart);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    Cart toEntity(CartDto cartDto);
}
