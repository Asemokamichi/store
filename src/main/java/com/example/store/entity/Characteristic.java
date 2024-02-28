package com.example.store.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "characteristics")
public class Characteristic {
    @Id
    @Column(name = "characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long characteristicId;
    @Column(name = "characteristic_name")
    private String characteristicName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "characteristic")
    List<ProductCharacteristic> productCharacteristics;

    public Characteristic() {
    }

    public Characteristic(long characteristicId, String characteristicName, Category category, List<ProductCharacteristic> productCharacteristics) {
        this.characteristicId = characteristicId;
        this.characteristicName = characteristicName;
        this.category = category;
        this.productCharacteristics = productCharacteristics;
    }

    public long getCharacteristicId() {
        return characteristicId;
    }

    public void setCharacteristicId(long characteristicId) {
        this.characteristicId = characteristicId;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public void setCharacteristicName(String characteristicName) {
        this.characteristicName = characteristicName;
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

    @Override
    public String toString() {
        return "Characteristic{" +
                "characteristicId=" + characteristicId +
                ", characteristicName='" + characteristicName + '\'' +
                ", category=" + category +
                '}';
    }
}