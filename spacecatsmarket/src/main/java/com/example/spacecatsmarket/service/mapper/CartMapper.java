package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Cart;
import com.example.spacecatsmarket.dto.cart.CartDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface CartMapper {
    CartDto toDto(Cart cart);
    Cart toEntity(CartDto cartDto);
}
