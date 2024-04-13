package com.example.store.dto;

import java.util.Arrays;

public class CategoryDto {
    private String name;
    private String[] options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "name='" + name + '\'' +
                ", options=" + Arrays.toString(options) +
                '}';
    }
}
