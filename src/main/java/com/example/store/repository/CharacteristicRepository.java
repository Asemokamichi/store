package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
