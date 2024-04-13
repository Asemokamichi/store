package com.example.store.entity;

import com.example.store.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Enumerated
    @Column(name = "role")
    private UserRole role;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_surname")
    private String userSurname;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Cart> carts;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Purchase> purchases;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Review> reviews;

    public String getFIO(){
        return userName + " " + userSurname;
    }
}
