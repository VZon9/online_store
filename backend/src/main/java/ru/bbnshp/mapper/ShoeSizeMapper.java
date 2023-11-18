package ru.bbnshp.mapper;

import ru.bbnshp.dto.ShoeSizeDto;
import ru.bbnshp.entities.ShoeSize;

public class ShoeSizeMapper {

    public static ShoeSizeDto toShoeSizeDto(ShoeSize size){
        return new ShoeSizeDto(SizeMapper.toSizeDto(size.getSize()), size.getExistingNum());
    }
}
