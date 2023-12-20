package ru.bbnshp.entities;

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

    @OneToMany(mappedBy = "order",  cascade = CascadeType.ALL)
    private Set<OrderShoe> orderSet = new HashSet<>();

    public void addOrder(OrderShoe orderShoe){
        orderSet.add(orderShoe);
        orderShoe.setOrder(this);
    }

    public void deleteOrder(OrderShoe orderShoe){
        orderSet.add(orderShoe);
        orderShoe.setOrder(null);
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

    public Set<OrderShoe> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderShoe> orderSet) {
        this.orderSet = orderSet;
    }
}
