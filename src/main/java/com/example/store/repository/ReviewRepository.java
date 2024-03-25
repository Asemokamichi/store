package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Product;
import com.example.store.entity.Review;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select avg(score) from Review where product = ?1 and access = true ")
    Double findAverageScoreByProduct(Product product);

    @Query("select count(*) from Review where user = ?1 and product = ?2")
    int findAllByProductAndUser(User user, Product product);

    @Query("from Review where product = ?1 and access = true or product = ?1 and user = ?2")
    List<Review> findAllByProductAndAccess(Product product, User user);

    @Query("select count(*) from Review where access = false")
    int findCountAllByAccess();

    List<Review> findAllByAccess(boolean flag);


}

//@Query("select count(*) from Review where (user = ?1  or access = true) and product = ?2")