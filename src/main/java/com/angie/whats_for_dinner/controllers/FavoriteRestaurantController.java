package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.data.FavoriteListRepository;
import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.models.FavoriteRestaurant;
import com.angie.whats_for_dinner.services.FavoriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/favorite-restaurants")
public class FavoriteRestaurantController {

    @Autowired
    private FavoriteRestaurantService favoriteRestaurantService;

    @PostMapping
    public ResponseEntity<FavoriteRestaurant> addRestaurant(@RequestBody FavoriteRestaurant restaurant) {
        FavoriteRestaurant savedRestaurant = favoriteRestaurantService.addRestaurant(restaurant.getRestaurantName(), restaurant.getRestaurantAddress());
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteRestaurant>> getFavoriteRestaurantsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteRestaurantService.getFavoriteRestaurantsByUser(userId));
    }
}

//@RestController
//@RequestMapping("/api/favorite-restaurants")
//public class FavoriteRestaurantController {
//
//    @Autowired
//    private FavoriteRestaurantRepository favoriteRestaurantRepository;
//
//    @Autowired
//    private FavoriteListRepository favoriteListRepository;
//
//    // Add a restaurant to a favorite list
//    @PostMapping
//    public FavoriteRestaurant addRestaurantToFavoriteList(@RequestBody FavoriteRestaurant favoriteRestaurant) {
//        FavoriteList favoriteList = favoriteListRepository.findById(favoriteRestaurant.getFavoriteList().getId())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid Favorite List ID"));
//
//        favoriteRestaurant.setFavoriteList(favoriteList);
//        return favoriteRestaurantRepository.save(favoriteRestaurant);
//    }
//
//
//    // Get all favorite restaurants for a specific user
//    @GetMapping("/user/{userId}")
//    public List<FavoriteRestaurant> getFavoriteRestaurantsByUser(@PathVariable Long userId) {
//        return favoriteRestaurantRepository.findByFavoriteList_UserId(userId);
//    }
//
//    @GetMapping("/random")
//    public FavoriteRestaurant getRandomFavoriteRestaurant(@RequestParam Long favoriteListId) {
//        List<FavoriteRestaurant> favoriteRestaurants = favoriteRestaurantRepository.findByFavoriteListId(favoriteListId);
//
//        if (favoriteRestaurants.isEmpty()) {
//            throw new IllegalArgumentException("No favorite restaurants found.");
//        }
//
//        return favoriteRestaurants.get(new Random().nextInt(favoriteRestaurants.size()));
//    }
//
//
//}