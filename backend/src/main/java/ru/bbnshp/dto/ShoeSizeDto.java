package ru.bbnshp.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import ru.bbnshp.entities.Size;

public class ShoeSizeDto {

    private Integer id;
    private SizeDto size;
    private Integer existingNum;

    public ShoeSizeDto(Integer id, SizeDto size, Integer existingNum) {
        this.id = id;
        this.size = size;
        this.existingNum = existingNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SizeDto getSize() {
        return size;
    }

    public void setSize(SizeDto size) {
        this.size = size;
    }

    public Integer getExistingNum() {
        return existingNum;
    }

    public void setExistingNum(Integer existingNum) {
        this.existingNum = existingNum;
    }
}
