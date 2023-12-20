package ru.bbnshp.dto;

import ru.bbnshp.entities.Shoe;
import ru.bbnshp.entities.Size;

public class BasketDto {

    private Integer id;

    private ShoeDto shoe;

    private SizeDto size;

    private Integer num;

    public BasketDto(Integer id, ShoeDto shoe, SizeDto size, Integer num) {
        this.id = id;
        this.shoe = shoe;
        this.size = size;
        this.num = num;
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

    public SizeDto getSize() {
        return size;
    }

    public void setSize(SizeDto size) {
        this.size = size;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
