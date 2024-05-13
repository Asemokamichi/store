package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductCharacteristicRepository extends JpaRepository<ProductCharacteristic, Long>, JpaSpecificationExecutor <Product> {
    Optional<ProductCharacteristic> findByCharacteristicAndProduct(Characteristic characteristic, Product product);

    @Query("select pc.characteristicValue from ProductCharacteristic pc where pc.characteristic = ?1 and pc.characteristic.filter = true group by pc.characteristicValue")
    List<String>findByCharacteristic(Characteristic characteristic);
}
