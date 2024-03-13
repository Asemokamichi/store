package com.example.store.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseId;
    @Enumerated
    @Column(name = "status")
    private Status status;
    @Column(name = "date_beg")
    private LocalDate dateBeg;

    @Column(name="address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "purchase")
    List<Order> orders;


    public Purchase() {
    }

    public Purchase(long purchaseId, int status, LocalDate dateBeg, List<Order> orders) {
        this.purchaseId = purchaseId;
        this.status = Status.values()[status];
        this.dateBeg = dateBeg;
        this.orders = orders;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(LocalDate dateBeg) {
        this.dateBeg = dateBeg;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "purchaseId=" + purchaseId +
                ", status=" + status +
                ", dateBeg=" + dateBeg +
                '}';
    }
}
