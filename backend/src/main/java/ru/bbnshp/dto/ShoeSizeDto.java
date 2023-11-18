package ru.bbnshp.dto;

import ru.bbnshp.entities.Size;

public class ShoeSizeDto {
    private SizeDto size;

    private Integer existingNum;

    public ShoeSizeDto(SizeDto size, Integer existingNum) {
        this.size = size;
        this.existingNum = existingNum;
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
