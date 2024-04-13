package com.example.store.dto;

import com.example.store.entity.Category;
import com.example.store.entity.Product;
import jakarta.persistence.*;

import java.util.Map;

public class ProductDto {
    private Long categoryId;
    private String name;
    private Long price;

    private Map<Long, String> options;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Map<Long, String> getOptions() {
        return options;
    }

    public void setOptions(Map<Long, String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", options=" + options +
                '}';
    }
}
