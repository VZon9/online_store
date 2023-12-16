package ru.bbnshp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_shoes")
public class OrderShoe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "shoes_id")
    private Shoe shoe;

    @ManyToOne
    @JoinColumn(name = "size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "num")
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
