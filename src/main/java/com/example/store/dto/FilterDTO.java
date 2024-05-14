package com.example.store.dto;

import com.example.store.entity.Characteristic;
import com.example.store.repository.CharacteristicRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Component
public class FilterDTO {
    private Map<Long, List<String>> filterMap;
}
