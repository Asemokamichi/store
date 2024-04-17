package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_characteristic")
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCharacteristic {
    @Id
    @Column(name = "product_characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCharacteristicId;
    @Column(name = "characteristic")
    private String characteristicValue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    @JsonIgnore
    private Characteristic characteristic;

    public long getProductCharacteristicId() {
        return productCharacteristicId;
    }

    public void setProductCharacteristicId(long productCharacteristicId) {
        this.productCharacteristicId = productCharacteristicId;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public void setCharacteristicValue(String characteristicValue) {
        this.characteristicValue = characteristicValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Characteristic getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(Characteristic characteristic) {
        this.characteristic = characteristic;
    }
}
