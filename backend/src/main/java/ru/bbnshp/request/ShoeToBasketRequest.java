package ru.bbnshp.request;

public class ShoeToBasketRequest {

    private Integer userId;

    private Integer shoeId;

    private Integer sizeId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShoeId() {
        return shoeId;
    }

    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }
}
