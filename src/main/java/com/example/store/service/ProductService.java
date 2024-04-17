package com.example.store.service;

import com.example.store.dto.CategoryDto;
import com.example.store.dto.NewProduct;
import com.example.store.dto.ProductDto;
import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import com.example.store.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Transactional
    public Page<Product> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);

        return productPage;
    }

    @Transactional
    public Product findById(Long productID) {
        return productRepository.findById(productID).orElse(null);
    }

    @Transactional
    public void deleteById(Long productID) {
        productRepository.deleteById(productID);
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public Product createProduct(ProductDto productDto) throws Exception {
        System.out.println(productDto);
        if (productRepository.findByProductName(productDto.getName()) != null) {
            throw new Exception("Данный товар уже создан!");
        }

        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        Product product = new Product();
        product.setCategory(category);
        product.setProductName(productDto.getName());
        product.setCost(productDto.getPrice());

        productRepository.save(product);

        Map<Long, String> options = productDto.getOptions();

        for (Long key : options.keySet()) {
            Characteristic characteristic = characteristicRepository.findById(key).orElse(null);
            if (characteristic == null) continue;

            ProductCharacteristic pc = new ProductCharacteristic();
            pc.setCharacteristic(characteristic);
            pc.setCharacteristicValue(options.get(key));
            pc.setProduct(product);

            productCharacteristicRepository.save(pc);
        }

        return product;
    }


    @Transactional
    public Product updateProduct(Long id, ProductDto productDto) throws Exception {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) throw new Exception();

        if (productDto.getName()!=null) {
            if (productRepository.findByProductName(productDto.getName()) != null) {
                throw new Exception("Данное название уже занято!");
            }
            product.setProductName(productDto.getName());
        }
        if (productDto.getPrice() != null) product.setCost(productDto.getPrice());

        productRepository.save(product);

        Map<Long, String> options = productDto.getOptions();

        for (Long key : options.keySet()) {
            Characteristic characteristic = characteristicRepository
                    .findByCharacteristicIdAndCategory(key, product.getCategory());

            if (characteristic == null || options.get(key).isEmpty()) continue;

            ProductCharacteristic pc = productCharacteristicRepository
                    .findByCharacteristicAndProduct(characteristic, product)
                    .orElse(new ProductCharacteristic());

            pc.setCharacteristic(characteristic);
            pc.setCharacteristicValue(options.get(key));
            pc.setProduct(product);

            productCharacteristicRepository.save(pc);
        }

        return product;
    }


    public List<NewProduct> findMostOrderedProducts(){
        return productRepository.findMostOrderedProducts();
    }


    @Transactional
    public List<Product> findAllByCategory(Category category){
        return productRepository.findAllByCategory(category);
    }
}





