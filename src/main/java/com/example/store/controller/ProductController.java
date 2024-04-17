package com.example.store.controller;

import com.example.store.entity.Product;
import com.example.store.entity.Review;
import com.example.store.entity.User;
import com.example.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductCharacteristicService productCharacteristicService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderService orderService;


    @GetMapping()
    public String products(@RequestParam(value = "page", required = false) Integer pageNumber, Model model) {
        if (pageNumber == null) pageNumber = 0;

        Page<Product> productPage = productService.findAll(pageNumber, 5);
        if (productPage.getTotalPages()<pageNumber) {
            productPage = productService.findAll(productPage.getTotalPages()-1, 5);
        }

        List<Product> products = productPage.getContent();


        int[] items = new int[productPage.getTotalPages()];
        for (int i = 0; i < items.length; i++) items[i] = i + 1;

        try {
            User user = userService.getUser();
            model.addAttribute("user", user);
        } catch (Exception e) {
            model.addAttribute("user", null);
        }

        model.addAttribute("products", products);
        model.addAttribute("page", productPage.getNumber());
        model.addAttribute("items", items);

        return "products/index";
    }

    @GetMapping("/details_product")
    public String detailsProduct(@RequestParam("product_id") Long product_id,
                                 Model model) {
        Product product = productService.findById(product_id);
        User user = userService.getUser();
        boolean checkOrder = false;

        if (user != null) {
             checkOrder = orderService.findAllByUserAndProduct(user, product);
            if (checkOrder) {
                boolean checkReview = reviewService.findAllByProductAndUser(user, product);
                model.addAttribute("checkReview", checkReview);
            }
        }else model.addAttribute("checkReview", true);

        List<Review> reviews = reviewService.findAllByProductAndAccess(product, user);
        float avgScore = reviewService.findAverageScoreByProduct(product);

        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgScore", avgScore);
        model.addAttribute("checkOrder", checkOrder);

        return "products/product_details";
    }


}
