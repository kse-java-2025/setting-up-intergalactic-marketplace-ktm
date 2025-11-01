package com.example.spacecatsmarket.service.mapper;

import com.example.spacecatsmarket.domain.Order;
import com.example.spacecatsmarket.dto.order.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { ProductMapper.class })
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
