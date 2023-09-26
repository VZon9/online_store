package org.example.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "firms")
public class Firm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "firm_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "firm", cascade = CascadeType.ALL)
    private Set<Shoes> shoesSet = new HashSet<>();

    private void addShoes(Shoes shoes){
        shoesSet.add(shoes);
        shoes.setFirm(this);
    }

    private void deleteShoes(Shoes shoes){
        shoesSet.remove(shoes);
        shoes.setFirm(null);
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

    public Set<Shoes> getShoes() {
        return shoesSet;
    }

    public void setShoes(Set<Shoes> shoesSet) {
        this.shoesSet = shoesSet;
    }
}
