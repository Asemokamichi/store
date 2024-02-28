package com.example.store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_characteristic")
public class ProductCharacteristic {
    @Id
    @Column(name = "product_characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCharacteristicId;
    @Column(name = "characteristic")
    private String characteristicValue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "characteristic_id")
    private Characteristic characteristic;

    public ProductCharacteristic() {
    }

    public ProductCharacteristic(long productCharacteristicId, String characteristicValue, Product product, Characteristic characteristic) {
        this.productCharacteristicId = productCharacteristicId;
        this.characteristicValue = characteristicValue;
        this.product = product;
        this.characteristic = characteristic;
    }

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

    @Override
    public String toString() {
        return "ProductCharacteristic{" +
                "productCharacteristicId=" + productCharacteristicId +
                ", characteristicValue='" + characteristicValue + '\'' +
                ", product=" + product +
                ", characteristic=" + characteristic +
                '}';
    }
}
