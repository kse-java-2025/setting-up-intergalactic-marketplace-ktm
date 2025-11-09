package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Order;
import com.example.spacecatsmarket.dto.order.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
