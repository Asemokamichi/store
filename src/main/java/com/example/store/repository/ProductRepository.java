package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostBetween(int minCost, int maxCost);

    List<Product> findAllByCategoryCategoryName(String categoryName);

//    List<Product> findAllByCategoryCategoryNameAndCostBetween(int minCost, int maxCost);
}
