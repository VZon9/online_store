package ru.bbnshp.mapper;

import ru.bbnshp.dto.ShoeDto;
import ru.bbnshp.entities.Shoe;

import java.util.stream.Collectors;

public class ShoeMapper {

    public static ShoeDto toShoeDto(Shoe shoe){
        return new ShoeDto(shoe.getId(), shoe.getColor(), shoe.getPrice(), shoe.getDescription(),
                            BrandMapper.toBrandDto(shoe.getBrand()),
                            shoe.getSex(),
                            TypeMapper.toTypeDto(shoe.getType()),
                            shoe.getName(),
                            shoe.getSizeSet().stream().map(ShoeSizeMapper::toShoeSizeDto).collect(Collectors.toSet()));
    }
}
