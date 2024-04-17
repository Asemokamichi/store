package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {
    Optional<ProductCharacteristic> findByCharacteristicAndProduct(Characteristic characteristic, Product product);

    @Query("select characteristicValue from ProductCharacteristic where characteristic = ?1 and characteristic.filter = true")
    Optional<List<String>> findByCharacteristic(Characteristic characteristic);
}
