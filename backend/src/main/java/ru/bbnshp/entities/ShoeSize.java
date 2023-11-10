package ru.bbnshp.entities;

import jakarta.persistence.*;
import ru.bbnshp.entities.composite_key.ShoeSizeKey;

@Entity
@Table(name = "shoes_sizes")
public class ShoeSize {

    @EmbeddedId
    private ShoeSizeKey id;

    @ManyToOne
    @MapsId("shoeId")
    @JoinColumn(name = "shoes_id")
    private Shoe shoe;

    @ManyToOne
    @MapsId("size")
    @JoinColumn(name = "size")
    private Size size;

    @Column(name = "num")
    private Integer existingNum;

    public ShoeSizeKey getId() {
        return id;
    }

    public void setId(ShoeSizeKey id) {
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
