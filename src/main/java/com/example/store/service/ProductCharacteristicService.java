package com.example.store.service;

import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import com.example.store.repository.CharacteristicRepository;
import com.example.store.repository.ProductCharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductCharacteristicService {
    @Autowired
    private ProductCharacteristicRepository productCharacteristicRepository;

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Transactional
    public ProductCharacteristic findById(Long id) {
        return productCharacteristicRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void save(ProductCharacteristic productCharacteristic) {
        productCharacteristicRepository.save(productCharacteristic);
    }

    @Transactional
    public void saveAll(List<Long> characteristicIDList, List<String> characteristicValueList) {
        if (characteristicIDList.size() == characteristicValueList.size() && !characteristicIDList.isEmpty()) {
            for (int i = 0; i < characteristicIDList.size(); i++) {
                ProductCharacteristic pc = findById(characteristicIDList.get(i));
                pc.setCharacteristicValue(characteristicValueList.get(i));

                save(pc);
            }
        }
    }

    @Transactional
    public void saveAllByProduct(Product product, List<Long> characteristicIDList, List<String> characteristicValueList) {
        if (characteristicIDList.size() == characteristicValueList.size() && !characteristicIDList.isEmpty()) {
            for (int i = 0; i < characteristicIDList.size(); i++) {
                if (characteristicValueList.get(i).isEmpty()) continue;

                ProductCharacteristic pc = new ProductCharacteristic();
                Characteristic c = characteristicRepository.getReferenceById(characteristicIDList.get(i));

                pc.setProduct(product);
                pc.setCharacteristic(c);
                pc.setCharacteristicValue(characteristicValueList.get(i));

                save(pc);
            }
        }
    }

    @Transactional
    public List<String> get(Characteristic characteristic) {
        return productCharacteristicRepository
                .findByCharacteristic(characteristic)
                .orElse(null);
    }
}
