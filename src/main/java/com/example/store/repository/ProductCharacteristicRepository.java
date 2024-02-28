package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long> {
}
