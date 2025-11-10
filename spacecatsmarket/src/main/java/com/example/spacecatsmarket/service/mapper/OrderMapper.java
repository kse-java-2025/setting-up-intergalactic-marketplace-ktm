package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Order;
import com.example.spacecatsmarket.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper {

    @Mapping(target = "items", source = "products")
    @Mapping(target = "createdAt", source = "orderDate")
    OrderDto toDto(Order order);

    @Mapping(target = "products", source = "items")
    @Mapping(target = "orderDate", source = "createdAt")
    Order toEntity(OrderDto orderDto);
}
