package com.example.store.service;

import com.example.store.entity.Cart;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import com.example.store.repository.CartRepository;
import com.example.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Cart> allByProducts() {
        return cartRepository.findAll();
    }

    // Добавить товар в корзину.
    @Transactional
    public void addProductToCart(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow();
        Cart cart = cartRepository.findAllByProduct(product);
        if (cart != null) {
            cart.setCount(cart.getCount() + 1);
        } else {
            cart = new Cart();

            User user = userService.getCurrentUser();

            cart.setUser(user);
            cart.setProduct(product);
            cart.setCount(1);
        }

        cartRepository.save(cart);
    }

    // Увеличить кол-во товара на 1.
    @Transactional
    public void increaseProductCount(long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        Cart cart = cartRepository.findAllByProduct(product);
        cart.setCount(cart.getCount() + 1);

        cartRepository.save(cart);
    }

    // Увеличить кол-во товара на 1.
    @Transactional
    public void decreaseProductCount(long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        Cart cart = cartRepository.findAllByProduct(product);
        cart.setCount(cart.getCount() - 1);

        if (cart.getCount()==0){
            cartRepository.delete(cart);
            return;
        }

        cartRepository.save(cart);
    }

    // Удалить товар из корзины.
    @Transactional
    public void deleteProduct(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow();
        cartRepository.deleteByProduct(product);
    }

}
