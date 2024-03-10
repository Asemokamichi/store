package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);

    Purchase findByPurchaseId(Long id);
}
