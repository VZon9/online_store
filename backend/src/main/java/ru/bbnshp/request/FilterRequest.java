package ru.bbnshp.request;

import ru.bbnshp.entities.Sex;

import java.util.List;

public class FilterRequest {

    private List<String> brands;

    private List<Sex> sex;

    private List<String> colors;

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<Sex> getSex() {
        return sex;
    }

    public void setSex(List<Sex> sex) {
        this.sex = sex;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
