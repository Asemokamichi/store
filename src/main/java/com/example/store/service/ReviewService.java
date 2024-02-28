package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.entity.Review;
import com.example.store.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public Review findById(Long id){
        return reviewRepository.findById(id).orElseThrow();
    }

    public Double findAverageScoreByProduct(Product product) {
        Double avgScore = reviewRepository.findAverageScoreByProduct(product);
        return Math.ceil(avgScore * 10) / 10;
    }
}
