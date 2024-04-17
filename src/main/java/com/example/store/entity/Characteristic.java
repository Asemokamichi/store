package com.example.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "characteristics")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Characteristic {
    @Id
    @Column(name = "characteristic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long characteristicId;
    @Column(name = "characteristic_name")
    private String characteristicName;
    @Column(name = "filter")
    private Boolean filter;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "characteristic")
    @JsonIgnore
    List<ProductCharacteristic> productCharacteristics;
}