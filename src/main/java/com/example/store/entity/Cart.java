package com.example.store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;
    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart() {
    }

    public Cart(long cartId, int count, User user, Product product) {
        this.cartId = cartId;
        this.count = count;
        this.user = user;
        this.product = product;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", count=" + count +
                ", user=" + user +
                ", product=" + product +
                '}';
    }
}
