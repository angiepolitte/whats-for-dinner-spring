package com.angie.whats_for_dinner.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

//*************  WITHOUT SPRING SECURITY - no difference  ****************
@Entity
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "username")
    @Size(min = 4, message = "Username must be at least 4 characters long")
    private String username;

    @Column(name= "password")
    @Size(min = 8, message = "Password must be at least 8 characters long")

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteList> favoriteLists = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteRestaurant> favoriteRestaurants = new ArrayList<>();

    public User() {}

    public User(Long id, String username, String password, List<FavoriteList> favoriteLists) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.favoriteLists = favoriteLists;
    }


}


//https://chatgpt.com/share/680eb638-2f54-800f-adef-8f223e8a746c
