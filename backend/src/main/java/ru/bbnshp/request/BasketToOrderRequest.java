package ru.bbnshp.request;

import ru.bbnshp.entities.Basket;

import java.util.List;

public class BasketToOrderRequest {
    private Integer userId;

    private List<Integer> basketsId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Integer> getBasketsId() {
        return basketsId;
    }

    public void setBasketsId(List<Integer> basketsId) {
        this.basketsId = basketsId;
    }
}
