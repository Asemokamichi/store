package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.Product;
import com.example.store.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select avg(r.score) from Review r where r.product = ?1")
    Double findAverageScoreByProduct(Product product);
}
