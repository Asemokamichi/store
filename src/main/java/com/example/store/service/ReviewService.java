package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.entity.Review;
import com.example.store.entity.User;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Transactional
    public Review findById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<Review> findAllByProductAndAccess(Product product, User user) {
        return reviewRepository.findAllByProductAndAccess(product, user);
    }

    @Transactional
    public float findAverageScoreByProduct(Product product) {
        Double avgScore = reviewRepository.findAverageScoreByProduct(product);
        if (avgScore == null) avgScore = 0.0;
        return (float) Math.round(avgScore * 100) / 100;
    }

    @Transactional
    public boolean findAllByProductAndUser(User user, Product product) {
        return reviewRepository.findAllByProductAndUser(user, product) != 0;
    }

    @Transactional
    public void addReview(String reviewVal, int score, Long productID, boolean flag) {
        Product product = productRepository.findById(productID).orElseThrow();
        User user = userService.getUser();

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(reviewVal);
        review.setScore(score);
        review.setAccess(flag);

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteById(Long reviewID) {
        reviewRepository.deleteById(reviewID);
    }

    @Transactional
    public void showReview(Long reviewID) {
        Review review = reviewRepository.getReferenceById(reviewID);
        review.setAccess(true);
    }

    @Transactional
    public int findCountAllByAccess() {
        return reviewRepository.findCountAllByAccess();
    }

    @Transactional
    public List<Review> findAllByAccess() {
        return reviewRepository.findAllByAccess(false);
    }
}
