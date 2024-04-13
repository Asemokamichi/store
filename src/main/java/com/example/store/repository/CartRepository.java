package com.example.store.repository;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findAllByUserAndProduct(User user, Product product);

    List<Cart> findAllByUserOrderByCartId(User user);

    @Query("select sum(count*product.cost) from Cart where user = ?1")
    int findSum(User user);

    void deleteByProduct(Product product);
}


//тсраница товар
//        оставить отзыв
//нельзя оставлять несколько отзыва

//Idea-a4bfaaa0=5f8b902b-8d20-406f-848c-63f4c56f7c30