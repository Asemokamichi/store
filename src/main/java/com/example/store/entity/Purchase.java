package com.example.store.entity;

import com.example.store.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "purchase")
    @JsonIgnore
    List<Order> orders;
}
