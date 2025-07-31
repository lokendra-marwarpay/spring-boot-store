package com.codewithmosh.store.mappers;

import org.mapstruct.Mapper;

import com.codewithmosh.store.dtos.OrderDto;
import com.codewithmosh.store.entities.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
