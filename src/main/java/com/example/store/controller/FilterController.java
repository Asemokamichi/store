package com.example.store.controller;

import com.example.store.dto.FilterDTO;
import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.repository.CharacteristicRepository;
import com.example.store.repository.specification.ProductSpecification;
import com.example.store.service.CategoryService;
import com.example.store.service.ProductCharacteristicService;
import com.example.store.service.ProductService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductCharacteristicService pcService;

    @Autowired
    private CharacteristicRepository characteristicRepository;


    @GetMapping
    public String filterMain(Model model) {
        System.out.println(new Category().getCategoryId());
        model.addAttribute("filterDto", new FilterDTO());
        model.addAttribute("products", productService.findAll());
        model.addAttribute("user", userService.getUser());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("currentCategory", null);
        model.addAttribute("flag", false);

        return "filter/index";
    }

    @GetMapping("/{category_id}")
    public String filterByCategory(@PathVariable Long category_id,
                                   @ModelAttribute FilterDTO filterDTO,
                                   @RequestParam(value = "max", required = false)Long max,
                                   @RequestParam(value = "min", required = false)Long min,
                                   Model model) {
        Category category = categoryService.findById(category_id);
        Map<Characteristic, List<String>> map = new LinkedHashMap<>();

        for (Characteristic c : category.getCharacteristics()) {
            List<String> characteristicValueList = pcService.productCharacteristicRepository(c);

            if (!characteristicValueList.isEmpty()) {
                map.put(c, characteristicValueList);
            }
        }

        Map<Characteristic, List<String>> filter = new HashMap<>();
        List<Product> products;

        System.out.printf("min = %d   max = %d\n", min, max);

        if (filterDTO.getFilterMap() != null) {
            for (Long id : filterDTO.getFilterMap().keySet()) {
                List<String> values = filterDTO.getFilterMap().get(id);
                if (values == null) continue;

                characteristicRepository.findById(id).ifPresent(characteristic -> filter.put(characteristic, values));
            }
            products = productService.findAll(ProductSpecification.byCharacteristic(category, filter, min, max));
        } else products = productService.findAllByCategory(category);

        LongSummaryStatistics stat = products.stream()
                .mapToLong(Product::getCost)
                .summaryStatistics();

        model.addAttribute("products", products);
        model.addAttribute("user", userService.getUser());
        model.addAttribute("filterDto", filterDTO.getFilterMap() == null ? new FilterDTO() : filterDTO);
        model.addAttribute("map", map);
        model.addAttribute("currentCategory", category);
        model.addAttribute("flag", false);
        model.addAttribute("max", stat == null ? 0 : stat.getMax());
        model.addAttribute("min", stat == null ? 0 : stat.getMin());
        return "filter/index";
    }
}