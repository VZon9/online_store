package ru.bbnshp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "shoes_sizes")
public class ShoeSize {
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

    @Column(name = "num")
    private Integer existingNum;

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

    public Integer getExistingNum() {
        return existingNum;
    }

    public void setExistingNum(Integer existingNum) {
        this.existingNum = existingNum;
    }
}
