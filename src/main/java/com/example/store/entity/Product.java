package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonIgnore
    private List<ProductCharacteristic> productCharacteristics;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<Cart> carts;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<Order> orders;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    List<Review> reviews;
}
