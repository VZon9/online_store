package ru.bbnshp.dto;

import ru.bbnshp.entities.*;

import java.util.HashSet;
import java.util.Set;

public class ShoeDto {

    private Integer id;

    private String color;

    private Integer price;

    private String description;

    private BrandDto brand;

    private Sex sex;

    private TypeDto type;

    private String name;

    private Set<ShoeSizeDto> sizeSet;

    public ShoeDto(Integer id, String color, Integer price, String description,
                   BrandDto brand, Sex sex, TypeDto type, String name, Set<ShoeSizeDto> sizeSet) {
        this.id = id;
        this.color = color;
        this.price = price;
        this.description = description;
        this.brand = brand;
        this.sex = sex;
        this.type = type;
        this.name = name;
        this.sizeSet = sizeSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public TypeDto getType() {
        return type;
    }

    public void setType(TypeDto type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ShoeSizeDto> getSizeSet() {
        return sizeSet;
    }

    public void setSizeSet(Set<ShoeSizeDto> sizeSet) {
        this.sizeSet = sizeSet;
    }
}
