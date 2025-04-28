package com.angie.whats_for_dinner.services;

import com.angie.whats_for_dinner.data.FavoriteListRepository;
import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.models.FavoriteRestaurant;
import com.angie.whats_for_dinner.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteListService {

    @Autowired
    private FavoriteListRepository favoriteListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRestaurantRepository favoriteRestaurantRepository;

    public List<FavoriteList> getAllFavoriteListsByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.map(User::getFavoriteLists).orElse(List.of());
    }

    public FavoriteList createFavoriteList(String name, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            FavoriteList favoriteList = new FavoriteList();
            favoriteList.setName(name);
            favoriteList.setUser(user);
            return favoriteListRepository.save(favoriteList);
        }
        // Handle case where user is not found (e.g., throw exception or return null)
        return null;
    }


    public void addRestaurantToList(Long listId, Long restaurantId) {
        FavoriteList list = favoriteListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("Favorite List not found"));

        FavoriteRestaurant restaurant = favoriteRestaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        list.getFavoriteRestaurants().add(restaurant);
        favoriteListRepository.save(list);
    }
}

