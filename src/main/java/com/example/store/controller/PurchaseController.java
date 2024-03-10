package com.example.store.controller;

import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import com.example.store.service.PurchaseService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PurchaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchases")
    public String purchases(Model model) {
        User user = userService.getUser();
        List<Purchase> purchases = purchaseService.findAllByUser(user);

        model.addAttribute("purchases", purchases);
        model.addAttribute("user", user);

        return "view/purchases";
    }

    @GetMapping("/purchases/details")
    public String orders(@RequestParam("purchase_id") Long purchaseID,
                         Model model) {
        User user = userService.getUser();
        Purchase purchase = purchaseService.findByPurchaseId(purchaseID);

        model.addAttribute("purchase", purchase);
        model.addAttribute("user", user);

        return "view/purchase_details";
    }
}
