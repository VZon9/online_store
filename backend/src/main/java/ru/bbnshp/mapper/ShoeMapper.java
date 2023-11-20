package ru.bbnshp.mapper;

import ru.bbnshp.dto.ShoeDto;
import ru.bbnshp.dto.ShoeSizeDto;
import ru.bbnshp.entities.Shoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoeMapper {

    public static ShoeDto toShoeDto(Shoe shoe){
        List<ShoeSizeDto> shoeSizeList = new ArrayList<>(shoe.getSizeSet().stream().map(ShoeSizeMapper::toShoeSizeDto).toList());
        shoeSizeList.sort(Comparator.comparing(o -> o.getSize().getValue()));
        return new ShoeDto(shoe.getId(), shoe.getColor(), shoe.getPrice(), shoe.getDescription(),
                            BrandMapper.toBrandDto(shoe.getBrand()),
                            shoe.getSex(),
                            TypeMapper.toTypeDto(shoe.getType()),
                            shoe.getName(),
                            shoeSizeList
                            );
    }
}
