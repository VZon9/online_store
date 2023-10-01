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
    private Set<Shoe> shoesSet = new HashSet<>();

    private void addShoes(Shoe shoe){
        shoesSet.add(shoe);
        shoe.setFirm(this);
    }

    private void deleteShoes(Shoe shoe){
        shoesSet.remove(shoe);
        shoe.setFirm(null);
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
