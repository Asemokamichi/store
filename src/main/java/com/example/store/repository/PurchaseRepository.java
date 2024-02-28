package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
