package com.example.store.controller;

import com.example.store.entity.Category;
import com.example.store.entity.Product;
import com.example.store.entity.Review;
import com.example.store.entity.User;
import com.example.store.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
            productPage = productService.findAll(productPage.getTotalPages(), 5);
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

        return "/view/product";
    }

    @PostMapping("/delete_product")
    public String deleteProduct(@RequestParam("product_id") Long productID,
                                @RequestParam("page") int page,
                                Model model) {
        return "redirect:/product?page=" + page;
    }

    //    @Transactional
    @GetMapping("/details_product")
    public String detailsProduct(@RequestParam("product_id") Long product_id,
                                 Model model) {
        Product product = productService.findById(product_id);
        User user = userService.getUser();

        if (user != null) {
            boolean checkOrder = orderService.findAllByUserAndProduct(user, product);
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

        return "view/details_product";
    }

    @PostMapping("/edit_product")
    public String editProduct(@RequestParam("product_id") Long product_id, Model model) {
        Product product = productService.findById(product_id);
        List<Category> categories = categoryService.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories);

        return "view/edit_product";
    }

    @PostMapping("/edit_product/submit")
    public String editProductSubmit(
            @RequestParam(value = "product_id", required = false) Long product_id,
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "cost", required = false) Long cost,
            @RequestParam(value = "characteristic_id") List<Long> characteristicIDList,
            @RequestParam(value = "characteristic_value") List<String> characteristicValueList
    ) {
        Product product = new Product();

        if (product_id != null) product = productService.findById(product_id);

        if (productName != "") product.setProductName(productName);

        if (cost != null) product.setCost(cost);

        productCharacteristicService.saveAll(characteristicIDList, characteristicValueList);

        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

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
}
