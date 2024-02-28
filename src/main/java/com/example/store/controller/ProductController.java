package com.example.store.controller;

import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import com.example.store.repository.*;
import com.example.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCharacteristicRepository productCharacteristicRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping()
    public String products(@RequestParam(value = "page", required = false) Integer pageNumber, Model model) {
        if (pageNumber == null) pageNumber = 0;

        Page<Product> productPage = productService.findAll(pageNumber, 5);
        List<Product> products = productPage.getContent();

        int[] items = new int[productPage.getTotalPages()];
        for (int i = 0; i < items.length; i++) items[i] = i + 1;

        model.addAttribute("products", products);
        model.addAttribute("page", productPage.getNumber());
        model.addAttribute("items", items);

        return "/view/product";
    }

    @PostMapping("/delete_product")
    public String deleteProduct(@RequestParam("product_id") Long productID,
                                @RequestParam("page") int page,
                                Model model) {
        productService.deleteById(productID);
        return "redirect:/product";
    }

    @GetMapping("/details_product")
    public String detailsProduct(@RequestParam("product_id") Long product_id,
                                 Model model) {
        Product product = productService.findById(product_id);
        Double avgScore = reviewRepository.findAverageScoreByProduct(product);

        model.addAttribute("product", product);
        if (avgScore != null) model.addAttribute("avgScore", Math.ceil(avgScore * 10) / 10);

        return "view/details_product";
    }

    @PostMapping("/edit_product")
    public String editProduct(@RequestParam("product_id") Long product_id, Model model) {
        Product product = productService.findById(product_id);
        List<Category> categories = categoryRepository.findAll();

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
        System.out.println(characteristicIDList);
        System.out.println(characteristicValueList);

        Product product = new Product();

        if (product_id != null) product = productService.findById(product_id);

        if (productName != "") product.setProductName(productName);

        if (cost != null) product.setCost(cost);

        if (characteristicIDList.size() == characteristicValueList.size() && !characteristicIDList.isEmpty()) {
            for (int i = 0; i < characteristicIDList.size(); i++) {
                System.out.println("abc");
                System.out.println(characteristicValueList.get(i));

                ProductCharacteristic pc = productCharacteristicRepository.findById(characteristicIDList.get(i)).orElseThrow();
                pc.setCharacteristicValue(characteristicValueList.get(i));

                productCharacteristicRepository.save(pc);
            }
        }

        productService.save(product);

        return "redirect:/product";
    }

    @GetMapping("/add_product")
    public String addProduct(Model model) {
        List<Category> categories = categoryRepository.findAll();
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
            Category category = categoryRepository.findById(categoryId).orElseThrow();
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
            @RequestParam(value = "characteristic_id") List<Long> characteristicsID,
            @RequestParam(value = "characteristic_value") List<String> characteristicsValue
    ) {
        Product product = productService.findById(productID);
        for (int i = 0; i < characteristicsID.size(); i++) {
            ProductCharacteristic pc = new ProductCharacteristic();
            Characteristic c = characteristicRepository.getReferenceById(characteristicsID.get(i));

            pc.setProduct(product);
            pc.setCharacteristic(c);
            pc.setCharacteristicValue(characteristicsValue.get(i));

            productCharacteristicRepository.save(pc);
        }

        return "redirect:/product";
    }
}
