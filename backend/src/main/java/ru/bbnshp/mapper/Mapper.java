package ru.bbnshp.mapper;

import ru.bbnshp.dto.*;
import ru.bbnshp.entities.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static BasketDto toBasketDto(Basket basket){
        return new BasketDto(basket.getId(), toShoeDto(basket.getShoe()), toSizeDto(basket.getSize()), basket.getNum());
    }

    public static BrandDto toBrandDto(Brand brand){
        return new BrandDto(brand.getName());
    }

    public static OrderShoeDto toOrderShoeDto(OrderShoe orderShoe){
        return new OrderShoeDto(orderShoe.getId(), toShoeDto(orderShoe.getShoe()), toSizeDto(orderShoe.getSize()), orderShoe.getNum());
    }

    public static OrderDto toOrderDto(Order order){
        return new OrderDto(order.getId(), order.getDate(), order.getStatus(),
                order.getOrderSet().stream().map(Mapper::toOrderShoeDto).collect(Collectors.toSet()));
    }

    public static ShoeDto toShoeDto(Shoe shoe){
        List<ShoeSizeDto> shoeSizeList = new ArrayList<>(shoe.getSizeSet().stream().map(Mapper::toShoeSizeDto).toList());
        shoeSizeList.sort(Comparator.comparing(o -> o.getSize().getValue()));
        return new ShoeDto(shoe.getId(), shoe.getColor(), shoe.getPrice(), shoe.getDescription(),
                Mapper.toBrandDto(shoe.getBrand()),
                shoe.getSex(),
                Mapper.toTypeDto(shoe.getType()),
                shoe.getName(),
                shoe.getImagePattern(),
                shoeSizeList
        );
    }

    public static ShoeSizeDto toShoeSizeDto(ShoeSize size){
        return new ShoeSizeDto(size.getId(), Mapper.toSizeDto(size.getSize()), size.getExistingNum());
    }

    public static SizeDto toSizeDto(Size size){
        return new SizeDto(size.getValue());
    }

    public static TypeDto toTypeDto(Type type){
        return new TypeDto(type.getName());
    }

    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(),
                user.getLogin(),
                user.getEmail(),
                user.getOrderSet().stream().map(Mapper::toOrderDto).collect(Collectors.toSet()),
                user.getBasketSet().stream().map(Mapper::toBasketDto).collect(Collectors.toSet()));
    }
}
