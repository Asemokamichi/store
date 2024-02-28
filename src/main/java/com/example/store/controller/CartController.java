package com.example.store.controller;

import com.example.store.entity.Cart;
import com.example.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping
    public String allByProducts(Model model){
        List<Cart> carts = cartService.allByProducts();
        model.addAttribute("carts", carts);

        return "view/cart";
    }

    @GetMapping("/into_a_basket")
    public String addProductToCart(@RequestParam("product_id") Long productID){
        cartService.addProductToCart(productID);

        return "redirect:/product";
    }

    @PostMapping("/increase_product_count")
    public String increaseProductCount(@RequestParam("product_id") Long productID){
        cartService.increaseProductCount(productID);

        return "redirect:/cart";
    }


    @PostMapping("/decrease_product_count")
    public String decreaseProductCount(@RequestParam("product_id") Long productID){
        cartService.decreaseProductCount(productID);

        return "redirect:/cart";
    }


    @PostMapping("/delete_product")
    public String deleteProduct(@RequestParam("product_id") Long productID){
        cartService.deleteProduct(productID);

        return "redirect:/cart";
    }
}
