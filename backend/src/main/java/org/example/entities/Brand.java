package org.example.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private Set<Shoe> shoesSet = new HashSet<>();

    private void addShoes(Shoe shoe){
        shoesSet.add(shoe);
        shoe.setBrand(this);
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

    public Set<Shoe> getShoes() {
        return shoesSet;
    }

    public void setShoes(Set<Shoe> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
