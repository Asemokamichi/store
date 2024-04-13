package com.example.store.repository;

import com.example.store.entity.Purchase;
import com.example.store.enums.Status;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUserOrderByDateBegAsc(User user);

    Purchase findByPurchaseId(Long id);

//    @Query("from Purchase where status!=?1 order by dateBeg")
    List<Purchase> findAllByStatusNotOrderByDateBegAsc(Status status);
}
