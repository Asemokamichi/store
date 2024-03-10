package com.example.store.repository;

import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Integer findAllByPurchaseUserAndProduct(User user, Product product);

    List<Order> findAllByPurchaseUser(User user);
}
