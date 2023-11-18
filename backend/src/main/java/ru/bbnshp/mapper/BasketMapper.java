package ru.bbnshp.mapper;

import ru.bbnshp.dto.BasketDto;
import ru.bbnshp.entities.Basket;

public class BasketMapper {

    public static BasketDto toBasketDto(Basket basket){
        return new BasketDto(basket.getId(), ShoeMapper.toShoeDto(basket.getShoe()));
    }
}
