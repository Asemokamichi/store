package com.example.store.service;

import com.example.store.entity.Category;
import com.example.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category>  findAll(){
        return categoryRepository.findAll();
    }
    @Transactional
    public Category findById(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
