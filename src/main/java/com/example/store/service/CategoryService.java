package com.example.store.service;

import com.example.store.dto.CategoryDto;
import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.repository.CategoryRepository;
import com.example.store.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    CharacteristicRepository characteristicRepository;

    @Transactional
    public List<Category>  findAll(){
        return categoryRepository.findAll();
    }

    @Transactional
    public Page<Category> findAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);

        return categoryPage;
    }

    @Transactional
    public Category createCategory(CategoryDto categoryDto)throws Exception{
        if (categoryRepository.findByCategoryName(categoryDto.getName())!=null){
            throw new Exception("Данная категория уже создана!");
        }
        Category category = new Category();
        category.setCategoryName(categoryDto.getName());

        categoryRepository.save(category);

        for (String obj: categoryDto.getOptions()){
            Characteristic characteristic = new Characteristic();
            characteristic.setCharacteristicName(obj);
            characteristic.setCategory(category);

            characteristicRepository.save(characteristic);
        }

        return category;
    }


    @Transactional
    public Category findById(Long categoryId){
        return categoryRepository.findById(categoryId).orElseThrow();
    }
}
