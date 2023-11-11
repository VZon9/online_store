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

    @OneToMany(mappedBy = "size")
    private Set<ShoeSize> sizeSet = new HashSet<>();

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
}
