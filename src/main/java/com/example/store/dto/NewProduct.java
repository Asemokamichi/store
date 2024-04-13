package com.example.store.dto;

import com.example.store.entity.Product;

public class NewProduct {
    private Product product;
    private Long orderCount;

    public NewProduct(Product product, Long orderCount) {
        this.product = product;
        this.orderCount = orderCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
