package com.example.store.service;

import com.example.store.entity.Purchase;
import com.example.store.entity.Status;
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
        return purchaseRepository.findAllByUserOrderByDateBegAsc(user);
    }

    @Transactional
    public Purchase findByPurchaseId(Long purchaseID){
        return purchaseRepository.findByPurchaseId(purchaseID);
    }

    @Transactional
    public List<Purchase> findAll(){
        return purchaseRepository.findAll();
    }

    @Transactional
    public List<Purchase> findAllByStatusNot(){
//        return purchaseRepository.findAll();
        return purchaseRepository.findAllByStatusNotOrderByDateBegAsc(Status.DELIVERED);
    }

    @Transactional
    public void statusNext(Long purchaseID){
        Purchase purchase = purchaseRepository.getById(purchaseID);
        purchase.setStatus(Status.values()[purchase.getStatus().ordinal()+1]);
    }

}
