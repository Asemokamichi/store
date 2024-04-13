package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_characteristic")
@Data
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
}
