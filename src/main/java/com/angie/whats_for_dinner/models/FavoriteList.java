package com.angie.whats_for_dinner.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class FavoriteList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "favorite_list_restaurants",
            joinColumns = @JoinColumn(name = "favorite_list_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_restaurant_id")
    )
    private List<FavoriteRestaurant> favoriteRestaurants = new ArrayList<>();

    public FavoriteList() {};

    public FavoriteList(Long id, String name, User user, List<FavoriteRestaurant> favoriteRestaurants) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.favoriteRestaurants = favoriteRestaurants;
    }
}

//public class FavoriteList {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String name;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @OneToMany(mappedBy = "favoriteList", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FavoriteRestaurant> favoriteRestaurants = new ArrayList<>();
//
//    public FavoriteList() {}
//
//    public FavoriteList(Long id, String name, User user, List<FavoriteRestaurant> favoriteRestaurants) {
//        this.id = id;
//        this.name = name;
//        this.user = user;
//        this.favoriteRestaurants = favoriteRestaurants;
//    }
//
//}

