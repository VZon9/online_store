package ru.bbnshp.dto;

import ru.bbnshp.entities.OrderStatus;
import ru.bbnshp.entities.Shoe;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class OrderDto {

    private Integer id;

    private Date date;

    private OrderStatus status;

    private Set<ShoeDto> shoesSet;

    public OrderDto(Integer id, Date date, OrderStatus status, Set<ShoeDto> shoesSet) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.shoesSet = shoesSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<ShoeDto> getShoesSet() {
        return shoesSet;
    }

    public void setShoesSet(Set<ShoeDto> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
