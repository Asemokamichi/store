package com.example.store.repository;

import com.example.store.dto.NewProduct;
import com.example.store.entity.Category;
import com.example.store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findByProductName(String name);


    /*
    * Получить топ три самых продоваемых товаров
    */
    @Query(value = "SELECT new com.example.store.dto.NewProduct(p, SUM(o.count)) " +
            "FROM Product p " +
            "join Order o on p = o.product " +
            "group by p " +
            "order by SUM(o.count) desc, p.productName " +
            "limit 3")
    List<NewProduct> findMostOrderedProducts();

    List<Product> findAllByCategory(Category category);
}
