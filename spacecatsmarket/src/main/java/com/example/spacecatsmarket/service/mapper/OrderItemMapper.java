package com.example.spacecatsmarket.service.mapper;
import com.example.spacecatsmarket.domain.OrderItem;
import com.example.spacecatsmarket.dto.order.OrderItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface OrderItemMapper {
    @Mapping(target = "productId", source = "product.id")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "product.id", source = "productId")
    OrderItem toEntity(OrderItemDto orderItemDto);
}

