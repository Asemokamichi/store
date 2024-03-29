package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductCharacteristicRepository productCharacteristicRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public Page<Product> findAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage;
    }

    @Transactional
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public Product findById(Long productID){
        return productRepository.findById(productID).orElse(null);
    }

    @Transactional
    public void deleteById(Long productID){
        productRepository.deleteById(productID);
    }

    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }
}
