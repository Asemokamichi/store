package com.example.store.controller;

import com.example.store.dto.FilterDTO;
import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.ProductCharacteristic;
import com.example.store.service.CategoryService;
import com.example.store.service.ProductCharacteristicService;
import com.example.store.service.ProductService;
import com.example.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @GetMapping
    public String filterMain(@RequestParam(value = "category_id", required = false) Long categoryID,
                             Model model) {
        Category category = null;
        Map<Characteristic, List<ProductCharacteristic>> map = new HashMap<>();

        if (categoryID != null) category = categoryService.findById(categoryID);

        if (category != null) {
            for (Characteristic c : category.getCharacteristics()) {
                List<ProductCharacteristic> characteristicValueList = pcService.productCharacteristicRepository(c);

                if (!characteristicValueList.isEmpty())
                    map.put(c, characteristicValueList);
            }
            model.addAttribute("products", productService.findAllByCategory(category));
        } else {
            model.addAttribute("products", productService.findAll());
        }

        var filterDto = new FilterDTO();
        for (Characteristic characteristic : map.keySet()) {
            filterDto.getFilterMap().put(characteristic.getCharacteristicId(), new ArrayList<>());
        }

        System.out.println(filterDto.getFilterMap());

        model.addAttribute("filterDto", filterDto);
        model.addAttribute("user", userService.getUser());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("map", map);
        model.addAttribute("currentCategory", category);

        return "filter";
    }

    @PostMapping("/action")
    public String handleFormSubmission(@RequestParam FilterDTO filterDTO) {
        System.out.println(filterDTO);

        return "redirect:/filter"; // Перенаправление на страницу success-page
    }
}
