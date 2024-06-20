package com.E_Commerce_Application.E_Commerce.site.Application.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private ERole role;

    @OneToMany(mappedBy = "user")
    private List<Address> address;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE},orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "user")
    private List<Product> product;

    @OneToMany(mappedBy = "user",cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = true)
    private List<Order> order;

    public User(){

    }
}

