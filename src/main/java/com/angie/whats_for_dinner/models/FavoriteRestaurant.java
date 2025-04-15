package com.angie.whats_for_dinner.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class FavoriteRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantName;
    private String restaurantAddress;

    @ManyToMany(mappedBy = "favoriteRestaurants")
    private List<FavoriteList> favoriteLists = new ArrayList<>();

    public FavoriteRestaurant() {};

    public FavoriteRestaurant(Long id, String restaurantName, String restaurantAddress, List<FavoriteList> favoriteLists) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.favoriteLists = favoriteLists;
    }
}


//public class FavoriteRestaurant {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Long restaurantId;
//
//    @ManyToOne
//    @JoinColumn(name = "favorite_list_id")
//    private FavoriteList favoriteList;
//
//    public FavoriteRestaurant() {}
//
//    public FavoriteRestaurant(Long id, Long restaurantId, FavoriteList favoriteList) {
//        this.id = id;
//        this.restaurantId = restaurantId;
//        this.favoriteList = favoriteList;
//    }
//
//}

