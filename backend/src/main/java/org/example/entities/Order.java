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
    private Date date;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "orders_shoes", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "shoes_id"))
    private Set<Shoe> shoesSet = new HashSet<>();

    public void addShoes(Shoe shoe){
        shoesSet.add(shoe);
        shoe.getOrderSet().add(this);
    }

    public void deleteShoe(Shoe shoe){
        shoesSet.remove(shoe);
        shoe.getOrderSet().remove(this);
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

    public void setDate(Date data) {
        this.date = data;
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

    public Set<Shoe> getShoesSet() {
        return shoesSet;
    }

    public void setShoesSet(Set<Shoe> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
