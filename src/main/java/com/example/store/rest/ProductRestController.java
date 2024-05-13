package com.example.store.rest;

import com.example.store.dto.ProductDto;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.repository.CharacteristicRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.specification.ProductSpecification;
import com.example.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

//    @GetMapping
//    public List<ProductDto> productList(){
//        List<Product> products =  productService.findAll();
//        return products.stream().map(ProductDto::from).toList();
//    }


    @PostMapping
    public Object createProduct(@RequestBody ProductDto productDto) {
        try {
            Product product = productService.createProduct(productDto);
            return product;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/{id}")
    public Object updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        try {
            Product product = productService.updateProduct(id, productDto);
            return product;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/j")
    public Object getTopProducts() {
        return productService.findMostOrderedProducts();
    }


    @GetMapping("/filter")
    public Object filterByFromAndTo(@RequestParam(value = "from", required = false) Integer from,
                                    @RequestParam(value = "to", required = false) Integer to) {
        List<Product> products = productRepository.findAll(ProductSpecification.byPrice(from, to));

        return  products;
    }

//    @GetMapping("/filterCharacteristic")
//    public Object filterByCharacteristic() {
//        Map<Characteristic, List<String>> filter = new HashMap<>();
//        filter.put(characteristicRepository.findById(1l).orElse(null),
//                List.of("Samsung")
//        );
//
//        filter.put(characteristicRepository.findById(2l).orElse(null),
//                List.of("Android", "Windows")
//        );
//
//        List<Product> products = productRepository.findAll(
//                ProductSpecification.byCharacteristic(filter));
//
//        return  products;
//    }
}
