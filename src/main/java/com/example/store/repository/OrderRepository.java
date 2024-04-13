package com.example.store.repository;

import com.example.store.dto.NewProduct;
import com.example.store.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Objects;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select count(*) from Order where purchase.user = ?1 and product = ?2")
    Integer findAllByPurchaseUserAndProduct(User user, Product product);

    List<Order> findAllByPurchasePurchaseId(Long id);

    @Query("select sum(count*product.cost) from Order where purchase.purchaseId = ?1")
    int findSum(Long purchaseID);

//    @Query(value = "SELECT NEW com.example.store.dto.NewProduct(o.product, SUM(o.count)) " +
//            "FROM Order o " +
//            "GROUP BY o.product " +
//            "ORDER BY SUM(o.count) desc " +
//            "LIMIT 3")

    @Query(value = "SELECT o.product " +
            "FROM Order o " +
            "GROUP BY o.product " +
            "ORDER BY SUM(o.count) desc " +
            "LIMIT 3")
    List<Product> findMostOrderedProducts();
}


