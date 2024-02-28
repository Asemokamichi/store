package com.example.store.repository;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findAllByProduct(Product product);

    void deleteByProduct(Product product);
}
