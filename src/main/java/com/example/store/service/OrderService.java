package com.example.store.service;

import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import com.example.store.repository.OrderRepository;
import com.example.store.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public List<Order> findAllByPurchasePurchaseId(Long id){
        return orderRepository.findAllByPurchasePurchaseId(id);
    }

    @Transactional
    public boolean findAllByUserAndProduct(User user, Product product){
        return orderRepository.findAllByPurchaseUserAndProduct(user, product) != 0;
    }

    @Transactional
    public int findSum(Long purchaseID){
        return orderRepository.findSum(purchaseID);
    }


}
