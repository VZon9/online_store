package ru.bbnshp.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sizes")
public class Size {

    @Id
    @Column(name = "size_value")
    private Integer value;

    @OneToMany(mappedBy = "size")
    private Set<ShoeSize> sizeSet = new HashSet<>();

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    private Set<Basket> basketSet = new HashSet<>();

    @OneToMany(mappedBy = "size")
    private Set<OrderShoe> orderSet = new HashSet<>();

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<ShoeSize> getSizeSet() {
        return sizeSet;
    }

    public void setSizeSet(Set<ShoeSize> sizeSet) {
        this.sizeSet = sizeSet;
    }

    public Set<Basket> getBasketSet() {
        return basketSet;
    }

    public void setBasketSet(Set<Basket> basketSet) {
        this.basketSet = basketSet;
    }

    public Set<OrderShoe> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderShoe> orderSet) {
        this.orderSet = orderSet;
    }
}
