package ru.bbnshp.mapper;

import ru.bbnshp.dto.TypeDto;
import ru.bbnshp.entities.Type;

public class TypeMapper {

    public static TypeDto toTypeDto(Type type){
        return new TypeDto(type.getName());
    }
}
