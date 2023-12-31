package ru.bbnshp.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "shoes")
public class Shoe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoes_id")
    private Integer id;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @Column(name = "bought_num")
    private Integer boughtNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "sex")
    @Enumerated(value = EnumType.STRING)
    private Sex sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "name")
    private String name;

    @Column(name = "image_pattern")
    private String imagePattern;

    @OneToMany(mappedBy = "shoe",  cascade = CascadeType.ALL)
    private Set<OrderShoe> orderSet = new HashSet<>();
    @OneToMany(mappedBy = "shoe",  cascade = CascadeType.ALL)
    private Set<ShoeSize> sizeSet = new HashSet<>();

    public void addSize(ShoeSize size){
        sizeSet.add(size);
        size.setShoe(this);
    }

    public void deleteSize(ShoeSize size){
        sizeSet.remove(size);
        size.setShoe(null);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getBoughtNum() {
        return boughtNum;
    }

    public void setBoughtNum(Integer boughtNum) {
        this.boughtNum = boughtNum;
    }

    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OrderShoe> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<OrderShoe> orderSet) {
        this.orderSet = orderSet;
    }

    public Set<ShoeSize> getSizeSet() {
        return sizeSet;
    }

    public void setSizeSet(Set<ShoeSize> sizeSet) {
        this.sizeSet = sizeSet;
    }

    public String getImagePattern() {
        return imagePattern;
    }

    public void setImagePattern(String imagePattern) {
        this.imagePattern = imagePattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, price, description, brand, sex, type, name);
    }
}
