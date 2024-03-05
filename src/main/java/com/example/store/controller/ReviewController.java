package com.example.store.controller;

import com.example.store.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/addReview")
    public String addReview(@RequestParam("review") String review,
                            @RequestParam("score") int score,
                            @RequestParam("product_id") Long productID){
        System.out.println(review);
        System.out.println(score);
        System.out.println(productID);
        reviewService.addReview(review, score, productID);

        return "redirect:/product/details_product?product_id=" + productID;
    }
}
