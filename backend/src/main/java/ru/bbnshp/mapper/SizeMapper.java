package ru.bbnshp.mapper;

import ru.bbnshp.dto.SizeDto;
import ru.bbnshp.entities.Size;

public class SizeMapper {

    public static SizeDto toSizeDto(Size size){
        return new SizeDto(size.getValue());
    }
}
