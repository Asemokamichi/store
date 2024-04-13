package com.example.store.service;

import com.example.store.entity.*;
import com.example.store.enums.Status;
import com.example.store.repository.CartRepository;
import com.example.store.repository.OrderRepository;
import com.example.store.repository.ProductRepository;
import com.example.store.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public List<Cart> findAllByUserOrderByCartId() {
        User user = userService.getUser();
        return cartRepository.findAllByUserOrderByCartId(user);
    }

    // Добавить товар в корзину.
    @Transactional
    public void addProductToCart(Long productID) {
        User user = userService.getUser();
        Product product = productRepository.findById(productID).orElseThrow();
        Cart cart = cartRepository.findAllByUserAndProduct(user, product);
        if (cart != null) {
            cart.setCount(cart.getCount() + 1);
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setCount(1);
        }

        cartRepository.save(cart);
    }

    @Transactional
    public void k(String address) {
        User user = userService.getUser();
        List<Cart> carts = cartRepository.findAllByUserOrderByCartId(user);
        Purchase purchase = new Purchase();
        purchase.setDateBeg(LocalDate.now());
        purchase.setAddress(address);
        purchase.setUser(user);
        purchase.setStatus(Status.CREATED);

        purchaseRepository.save(purchase);
        "hello".intern();

        for (Cart cart : carts) {
            Order order = new Order();
            order.setPurchase(purchase);
            order.setProduct(cart.getProduct());
            order.setCount(cart.getCount());

            orderRepository.save(order);

            cartRepository.delete(cart);
        }

    }

    // Увеличить кол-во товара на 1.
    @Transactional
    public void increaseProductCount(long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        User user = userService.getUser();
        Cart cart = cartRepository.findAllByUserAndProduct(user, product);
        cart.setCount(cart.getCount() + 1);

        cartRepository.save(cart);
    }

    // Увеличить кол-во товара на 1.
    @Transactional
    public void decreaseProductCount(long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        User user = userService.getUser();
        Cart cart = cartRepository.findAllByUserAndProduct(user, product);
        cart.setCount(cart.getCount() - 1);

        if (cart.getCount() == 0) {
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

    @Transactional
    public int findSum(User user) {
        return cartRepository.findSum(user);
    }

}
