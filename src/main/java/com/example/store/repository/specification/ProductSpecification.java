package com.example.store.repository.specification;

import com.example.store.dto.FilterDTO;
import com.example.store.entity.Category;
import com.example.store.entity.Characteristic;
import com.example.store.entity.Product;
import com.example.store.entity.ProductCharacteristic;
import com.example.store.repository.CharacteristicRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductSpecification {
    private CharacteristicRepository characteristicRepository;

    public static Specification<Product> byPrice(Integer from, Integer to) {
        return (root, query, criteriaBuilder) -> {
            System.out.println(from);
            System.out.println(to);
            List<Predicate> predicates = new ArrayList<>();

            if (from != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), from)
                );
            }

            if (to != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("cost"), to)
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Product> byCharacteristic(Category category, Map<Characteristic, List<String>> filter, Long from, Long to) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(
                    criteriaBuilder.equal(root.get("category"), category)
            );

            for (Characteristic characteristic : filter.keySet()) {
                List<String> values = filter.get(characteristic);
                if (values == null) continue;

                Join<Product, ProductCharacteristic> join = root.join("productCharacteristics", JoinType.INNER);

                join.on(criteriaBuilder.equal(join.get("characteristic"), characteristic));

                List<Predicate> joinPredicates = new ArrayList<>();

                for (String value : values) {
                    joinPredicates.add(
                            criteriaBuilder.equal(join.get("characteristicValue"), value)
                    );
                }

                join.on(criteriaBuilder.or(joinPredicates.toArray(new Predicate[0])));
            }

            if (from != null) {
                predicates.add(
                        criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), from)
                );
            }

            if (to != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("cost"), to)
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}


