package com.example.store.rest;

import com.example.store.dto.CategoryDto;
import com.example.store.dto.PageDto;
import com.example.store.dto.ProductDto;
import com.example.store.entity.Category;
import com.example.store.entity.Product;
import com.example.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
    @Autowired
    private ProductService productService;

//    @GetMapping
//    public List<ProductDto> productList(){
//        List<Product> products =  productService.findAll();
//        return products.stream().map(ProductDto::from).toList();
//    }

    @GetMapping()
    public PageDto productListByPage(@RequestParam(value = "page", required = false) Integer page) {
        if (page == null) page = 0;
        Page<Product> productPage = productService.findAll(page, 5);

        PageDto pageDto = new PageDto(productPage);

        return pageDto;
    }

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


    // `/products/7` (POST) -> update
    // {
    //   "name": "new name...",
    //   "options": [
    //     5: "new option value"
    //   ]
    // }
}
