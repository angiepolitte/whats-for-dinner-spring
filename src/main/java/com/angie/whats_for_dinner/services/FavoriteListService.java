package com.angie.whats_for_dinner.services;

import com.angie.whats_for_dinner.data.FavoriteListRepository;
import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.models.FavoriteRestaurant;
import com.angie.whats_for_dinner.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteListService {

    @Autowired
    private FavoriteListRepository favoriteListRepository;

    @Autowired
    private UserRepository userRepository;

    public FavoriteList createFavoriteList(String listName, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FavoriteList newList = new FavoriteList();
        newList.setName(listName);
        newList.setUser(user);

        return favoriteListRepository.save(newList);
    }
    public void addRestaurantToList(Long listId, Long restaurantId, FavoriteRestaurantRepository favoriteRestaurantRepository) {
        FavoriteList list = favoriteListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Favorite List not found"));

        FavoriteRestaurant restaurant = favoriteRestaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        list.getFavoriteRestaurants().add(restaurant);
        favoriteListRepository.save(list);
    }
}

