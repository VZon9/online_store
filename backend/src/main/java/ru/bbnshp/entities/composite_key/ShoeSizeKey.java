package ru.bbnshp.entities.composite_key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShoeSizeKey implements Serializable {

    @Column(name = "shoes_id")
    private Integer shoeId;

    @Column(name = "size")
    private Integer size;

    public Integer getShoeId() {
        return shoeId;
    }

    public void setShoeId(Integer shoeId) {
        this.shoeId = shoeId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoeSizeKey that = (ShoeSizeKey) o;
        return Objects.equals(shoeId, that.shoeId) && Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoeId, size);
    }
}
