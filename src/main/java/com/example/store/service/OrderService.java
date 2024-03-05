package com.example.store.service;

import com.example.store.entity.Order;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import com.example.store.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Order> findAllByPurchaseUser(){
        User user = userService.getCurrentUser();
        return orderRepository.findAllByPurchaseUser(user);
    }

    @Transactional
    public boolean findAllByUserAndProduct(User user, Product product){

        return orderRepository.findAllByUserAndProduct(user, product) != 0;
    }
}
