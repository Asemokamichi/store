package com.example.store.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;
    @Column(name = "count")
    private int count;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    public Order() {
    }

    public Order(long orderId, int count, Product product, Purchase purchase) {
        this.orderId = orderId;
        this.count = count;
        this.product = product;
        this.purchase = purchase;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", count=" + count +
                ", product=" + product +
                ", purchase=" + purchase +
                '}';
    }
}
