package com.example.store.controller;

import com.example.store.entity.Order;
import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import com.example.store.repository.PurchaseRepository;
import com.example.store.service.OrderService;
import com.example.store.service.PurchaseService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;


}
