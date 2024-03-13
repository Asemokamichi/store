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
                            @RequestParam("product_id") Long productID,
                            @RequestParam(value = "public", required = false) String publicValue) {
        boolean flag = publicValue != null;
        reviewService.addReview(review, score, productID, flag);

        return "redirect:/product/details_product?product_id=" + productID;
    }
}
