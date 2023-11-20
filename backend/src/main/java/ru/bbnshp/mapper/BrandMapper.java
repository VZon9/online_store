package ru.bbnshp.mapper;

import ru.bbnshp.dto.BrandDto;
import ru.bbnshp.entities.Brand;

public class BrandMapper {

    public static BrandDto toBrandDto(Brand brand){
        return new BrandDto(brand.getName());
    }
}
