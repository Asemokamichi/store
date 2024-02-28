package com.example.store.service;

import com.example.store.entity.Product;
import com.example.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<Product> findAll(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long productID){
        Product product = productRepository.findById(productID).orElseThrow();
        return product;
    }

    public void deleteById(Long productID){
        productRepository.deleteById(productID);
    }

    public void save(Product product){
        productRepository.save(product);
    }
}
