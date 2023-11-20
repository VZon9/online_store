package ru.bbnshp.dto;

import ru.bbnshp.entities.Shoe;

public class BasketDto {

    private Integer id;

    private ShoeDto shoe;

    public BasketDto(Integer id, ShoeDto shoe) {
        this.id = id;
        this.shoe = shoe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ShoeDto getShoe() {
        return shoe;
    }

    public void setShoe(ShoeDto shoe) {
        this.shoe = shoe;
    }
}
