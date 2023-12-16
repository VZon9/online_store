package ru.bbnshp.dto;

import ru.bbnshp.entities.OrderStatus;

import java.sql.Date;
import java.util.Set;

public class OrderDto {

    private Integer id;

    private Date date;

    private OrderStatus status;

    private Set<OrderShoeDto> orderSet;

    public OrderDto(Integer id, Date date, OrderStatus status, Set<OrderShoeDto> orderSet) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.orderSet = orderSet;
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

    public Set<OrderShoeDto> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderShoeDto> orderSet) {
        this.orderSet = orderSet;
    }
}
