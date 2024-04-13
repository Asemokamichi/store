package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmailAndPassword(String email, String password);
    User getByEmail(String email);
}
