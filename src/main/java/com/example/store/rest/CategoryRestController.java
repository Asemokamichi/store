package com.example.store.rest;

import com.example.store.dto.CategoryDto;
import com.example.store.dto.PageDto;
import com.example.store.entity.Category;
import com.example.store.entity.Product;
import com.example.store.repository.CategoryRepository;
import com.example.store.service.CategoryService;
import com.example.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
    @Autowired
    private CategoryService categoryService;

//    @GetMapping
//    public List<ProductDto> productList(){
//        List<Product> products =  productService.findAll();
//        return products.stream().map(ProductDto::from).toList();
//    }

    @GetMapping()
    public PageDto categoryListByPage(@RequestParam(value = "page", required = false) Integer page) {
        if (page == null) page = 0;
        Page<Category> categoryPage = categoryService.findAll(page, 5);
        PageDto pageDto = new PageDto(categoryPage);

        return pageDto;
    }

    @PostMapping
    public Object createCategory(@RequestBody CategoryDto categoryDto) {
        try{
            Category category = categoryService.createCategory(categoryDto);
            return category;
        } catch (Exception e) {
           return e.getMessage();
        }
    }
}
