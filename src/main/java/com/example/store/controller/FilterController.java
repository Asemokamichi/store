package com.example.store.controller;

import com.example.store.dto.FilterDTO;
import com.example.store.dto.MaxAndMinResultProducts;
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
        Object j = null;
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
                                   @RequestParam(value = "currentMax", required = false) Long currentMax,
                                   @RequestParam(value = "currentMin", required = false) Long currentMin,
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

        System.out.printf("min = %d   max = %d\n", currentMin, currentMax);

        if (filterDTO.getFilterMap() != null) {
            for (Long id : filterDTO.getFilterMap().keySet()) {
                List<String> values = filterDTO.getFilterMap().get(id);
                if (values == null) continue;

                characteristicRepository.findById(id).ifPresent(characteristic -> filter.put(characteristic, values));
            }
            products = productService.findAll(
                    ProductSpecification.byCharacteristic(category, filter, currentMin, currentMax)
            );
        } else products = productService.findAllByCategory(category);

//        LongSummaryStatistics stat = products.stream()
//                .mapToLong(Product::getCost)
//                .summaryStatistics();

        MaxAndMinResultProducts maxAndMin = productService.MaxAndMinFindByCategory(category);

        model.addAttribute("user", userService.getUser());
        model.addAttribute("products", products);
        model.addAttribute("filterDto", filterDTO.getFilterMap() == null ? new FilterDTO() : filterDTO);
        model.addAttribute("map", map);
        model.addAttribute("currentCategory", category);
        model.addAttribute("flag", false);

        model.addAttribute("max", maxAndMin.getMax());
        model.addAttribute("min", maxAndMin.getMin());

        model.addAttribute("currentMax", currentMax == null ? maxAndMin.getMax() : currentMax);
        model.addAttribute("currentMin", currentMin == null ? maxAndMin.getMin() : currentMin);
        return "filter/index";
    }
}