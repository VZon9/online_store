package ru.bbnshp.entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private List<Shoe> shoesList = new ArrayList<>();

    private void addShoes(Shoe shoe){
        shoesList.add(shoe);
        shoe.setType(this);
    }

    private void deleteShoes(Shoe shoe){
        shoesList.remove(shoe);
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
    public List<Shoe> getShoesList() {
        return shoesList;
    }

    public void ListShoesList(List<Shoe> shoesList) {
        this.shoesList = shoesList;
    }
}
