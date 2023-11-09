package ru.bbnshp.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sizes")
public class Size {

    @Id
    @Column(name = "size_value")
    Integer value;

    @ManyToMany
    @JoinTable(name = "shoes_sizes", joinColumns = @JoinColumn(name = "size"), inverseJoinColumns = @JoinColumn(name = "shoes_id"))
    private Set<Shoe> shoesSet = new HashSet<>();

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Set<Shoe> getShoesSet() {
        return shoesSet;
    }

    public void setShoesSet(Set<Shoe> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
