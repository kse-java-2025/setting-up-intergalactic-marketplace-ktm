package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.CartItem;
import com.example.spacecatsmarket.dto.cart.CartItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface CartItemMapper {

    @Mapping(target = "productId", source = "product.id")
    CartItemDto toDto(CartItem cartItem);

    @Mapping(target = "product.id", source = "productId")
    CartItem toEntity(CartItemDto cartItemDto);
}

