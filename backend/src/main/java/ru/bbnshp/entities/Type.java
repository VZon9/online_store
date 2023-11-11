package ru.bbnshp.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shoe_types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private Set<Shoe> shoesSet = new HashSet<>();

    private void addShoes(Shoe shoe){
        shoesSet.add(shoe);
        shoe.setType(this);
    }

    private void deleteShoes(Shoe shoe){
        shoesSet.remove(shoe);
        shoe.setBrand(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Set<Shoe> getShoesList() {
        return shoesSet;
    }

    public void setShoesSet(Set<Shoe> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
