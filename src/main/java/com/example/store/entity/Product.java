package com.example.store.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @Column(name = "product_name")
    private String productName;
    @Column(name="cost")
    private long cost;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductCharacteristic> productCharacteristics;

    @OneToMany(mappedBy = "product")
    List<Cart> carts;

    @OneToMany(mappedBy = "product")
    List<Order> orders;

    @OneToMany(mappedBy = "product")
    List<Review> reviews;

    public Product() {
    }

    public Product(long productId, String productName, long cost, Category category, List<ProductCharacteristic> productCharacteristics, List<Cart> carts, List<Order> orders, List<Review> reviews) {
        this.productId = productId;
        this.productName = productName;
        this.cost = cost;
        this.category = category;
        this.productCharacteristics = productCharacteristics;
        this.carts = carts;
        this.orders = orders;
        this.reviews = reviews;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ProductCharacteristic> getProductCharacteristics() {
        return productCharacteristics;
    }

    public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
        this.productCharacteristics = productCharacteristics;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
