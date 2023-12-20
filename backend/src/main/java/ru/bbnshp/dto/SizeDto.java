package ru.bbnshp.dto;

public class SizeDto {
    private Integer value;

    public SizeDto(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
