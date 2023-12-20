package ru.bbnshp.request;

import ru.bbnshp.entities.Sex;
import ru.bbnshp.entities.Type;

import java.util.List;

public class FilterRequest {

    private List<String> brands;

    private List<String> types;

    private List<String> colors;

    public List<String> getBrands() {
        return brands;
    }

    public void setBrands(List<String> brands) {
        this.brands = brands;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
