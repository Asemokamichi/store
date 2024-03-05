package com.example.store.controller;

import com.example.store.entity.Order;
import com.example.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/my_purchases")
    public String myPurchases(Model model){
        List<Order> orders = orderService.findAllByPurchaseUser();
        model.addAttribute("orders", orders);
        return "view/orders";
    }
}
