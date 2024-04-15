package com.example.store.controller;

import com.example.store.entity.*;
import com.example.store.service.*;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired ProductCharacteristicService productCharacteristicService;

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

    @PostMapping("/delete_product")
    public String deleteProduct(@RequestParam("product_id") Long productID,
                                @RequestParam("page") int page,
                                Model model) {
        productService.deleteById(productID);

        return "redirect:/product?page=" + page;
    }

    @PostMapping("/edit_product")
    public String editProduct(@RequestParam("product_id") Long product_id, Model model) {
        Product product = productService.findById(product_id);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", userService.getUser());
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "view/edit_product";
    }

    @PostMapping("/edit_product/submit")
    public String editProductSubmit(
            @RequestParam(value = "product_id", required = false) Long product_id,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "cost", required = false) Long cost,
            @RequestParam(value = "characteristic_id", required = false) List<Long> characteristicIDList,
            @RequestParam(value = "characteristic_value", required = false) List<String> characteristicValueList
    ) {
        Product product = new Product();

        if (product_id != null) product = productService.findById(product_id);

        if (productName != "") product.setProductName(productName);

        if (cost != null) product.setCost(cost);

        if (characteristicIDList !=null &&
                characteristicValueList!=null
        )productCharacteristicService.saveAll(characteristicIDList, characteristicValueList);

        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.findAll();

        model.addAttribute("user", userService.getUser());
        model.addAttribute("categories", categories);
        model.addAttribute("user", userService.getUser());

        return "view/add_product";
    }

    @PostMapping("/add_product/submit")
    public String addProductSubmit(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "cost", required = false) Long cost,
            Model model
    ) {
        Product product = new Product();

        if (productName != "") product.setProductName(productName);

        if (categoryId != null) {
            Category category = categoryService.findById(categoryId);
            product.setCategory(category);
        }

        if (cost != null) product.setCost(cost);

        productService.save(product);

        model.addAttribute("user", userService.getUser());
        model.addAttribute("product", product);

        return "view/add_characteristics";
    }

    @PostMapping("/add_characteristics/submit")
    public String addCharacteristicsSubmit(
            @RequestParam(value = "product_id") Long productID,
            @RequestParam(value = "characteristic_id") List<Long> characteristicIDList,
            @RequestParam(value = "characteristic_value") List<String> characteristicValueList
    ) {
        Product product = productService.findById(productID);
        productCharacteristicService.saveAllByProduct(product, characteristicIDList, characteristicValueList);

        return "redirect:/product";
    }

    @PostMapping("/private_review")
    public String privateReview(@RequestParam(value = "review_id") Long reviewID){
        reviewService.updatePrivateReview(reviewID);

        return "redirect:/product/details_product";
    }

}
