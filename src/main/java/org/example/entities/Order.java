package org.example.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "orders_shoes", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "shoes_id"))
    private Set<Shoes> shoesSet = new HashSet<>();

    public void addShoes(Shoes shoes){
        shoesSet.add(shoes);
        shoes.getOrderSet().add(this);
    }

    public void deleteShoes(Shoes shoes){
        shoesSet.remove(shoes);
        shoes.getOrderSet().remove(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Shoes> getShoesSet() {
        return shoesSet;
    }

    public void setShoesSet(Set<Shoes> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
