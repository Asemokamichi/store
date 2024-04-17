package com.example.store.dto;

import com.example.store.entity.Characteristic;
import com.example.store.entity.ProductCharacteristic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilterDTO {
    private Map<Long, List<ProductCharacteristic>> filterMap = new HashMap<>();

    public Map<Long, List<ProductCharacteristic>> getFilterMap() {
        return filterMap;
    }

    public void setFilterMap(Map<Long, List<ProductCharacteristic>> filterMap) {
        this.filterMap = filterMap;
    }
}
