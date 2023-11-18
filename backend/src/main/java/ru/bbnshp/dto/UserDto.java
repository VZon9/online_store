package ru.bbnshp.dto;



import java.util.Set;

public class UserDto {

    private Integer id;

    private String login;

    private String email;

    private Set<OrderDto> orderSet;

    public UserDto(Integer id, String login, String email, Set<OrderDto> orderSet, Set<BasketDto> basket) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.orderSet = orderSet;
        this.basket = basket;
    }

    private Set<BasketDto> basket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<OrderDto> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderDto> orderSet) {
        this.orderSet = orderSet;
    }

    public Set<BasketDto> getBasket() {
        return basket;
    }

    public void setBasket(Set<BasketDto> basket) {
        this.basket = basket;
    }
}
