package com.angie.whats_for_dinner.services;

import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
import com.angie.whats_for_dinner.models.FavoriteRestaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteRestaurantService {

    @Autowired
    private FavoriteRestaurantRepository favoriteRestaurantRepository;

    public FavoriteRestaurant addRestaurant(String name, String address) {
        FavoriteRestaurant restaurant = new FavoriteRestaurant();
        restaurant.setRestaurantName(name);
        restaurant.setRestaurantAddress(address);
        return favoriteRestaurantRepository.save(restaurant);
    }

    public List<FavoriteRestaurant> getFavoriteRestaurantsByUser(Long userId) {
        return favoriteRestaurantRepository.findByFavoriteLists_UserId(userId);
    }
}
