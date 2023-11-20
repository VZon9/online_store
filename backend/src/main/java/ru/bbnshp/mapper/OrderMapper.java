package ru.bbnshp.mapper;

import ru.bbnshp.dto.OrderDto;
import ru.bbnshp.entities.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto toOrderDto(Order order){
        return new OrderDto(order.getId(), order.getDate(), order.getStatus(),
                            order.getShoesSet().stream().map(ShoeMapper::toShoeDto).collect(Collectors.toSet()));
    }
}
