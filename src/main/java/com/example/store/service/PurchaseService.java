package com.example.store.service;

import com.example.store.entity.Purchase;
import com.example.store.entity.User;
import com.example.store.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public List<Purchase> findAllByUser(User user){
        return purchaseRepository.findAllByUser(user);
    }

    @Transactional
    public Purchase findByPurchaseId(Long purchaseID){
        return purchaseRepository.findByPurchaseId(purchaseID);
    }
}
