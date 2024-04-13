package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {
    Optional<ProductCharacteristic> findByCharacteristicAndProduct(Characteristic characteristic, Product product);
}
