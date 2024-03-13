package com.example.store.controller;

import com.example.store.entity.Purchase;
import com.example.store.entity.Review;
import com.example.store.entity.User;
import com.example.store.service.PurchaseService;
import com.example.store.service.ReviewService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("moderation")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping()
    public String moderation(Model model){
        User user = userService.getUser();
        int numberPrivateReviews = reviewService.findCountAllByAccess();
        int numberPurchases = purchaseService.findAllByStatusNot().size();

        model.addAttribute("user", user);
        model.addAttribute("numberPrivateReviews", numberPrivateReviews);
        model.addAttribute("numberPurchases", numberPurchases);

        return "view/moderation";
    }



    @GetMapping("/reviews")
    public String moderationReviews(Model model){
        User user = userService.getUser();
        List<Review> reviews = reviewService.findAllByAccess();

        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);

        return "view/moderation_reviews";
    }

    @PostMapping("/reviews/delete")
    public String moderationReviewsDelete(@RequestParam("review_id")Long reviewID){
        reviewService.deleteById(reviewID);

        return "redirect:/moderation/reviews";
    }

    @PostMapping("/reviews/show")
    public String moderationReviewsShow(@RequestParam("review_id")Long reviewID){
        reviewService.showReview(reviewID);

        return "redirect:/moderation/reviews";
    }

    @GetMapping("/purchases")
    public String moderationPurchases(Model model){
        User user = userService.getUser();
        List<Purchase> purchases = purchaseService.findAllByStatusNot();

        model.addAttribute("user", user);
        model.addAttribute("purchases", purchases);

        return "view/moderation_purchases";
    }

    @PostMapping("/purchases/next")
    public String moderationPurchases(@RequestParam("purchase_id")Long purchaseId){
        purchaseService.statusNext(purchaseId);

        return "redirect:/moderation/purchases";
    }

}
