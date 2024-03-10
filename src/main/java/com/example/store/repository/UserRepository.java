package com.example.store.repository;

import com.example.store.entity.Category;
import com.example.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmailAndPassword(String email, String password);
}
