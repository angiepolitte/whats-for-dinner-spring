package com.angie.whats_for_dinner.data;

import com.angie.whats_for_dinner.models.FavoriteRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteRestaurantRepository extends JpaRepository<FavoriteRestaurant, Long> {
    List<FavoriteRestaurant> findByFavoriteLists_UserId(Long userId);
}

//public interface FavoriteRestaurantRepository extends CrudRepository<FavoriteRestaurant, Long> {
//    List<FavoriteRestaurant> findByFavoriteList_UserId(Long userId);  // Get restaurants by favorite list ID
//    List<FavoriteRestaurant> findByFavoriteListId(Long favoriteListId);
//}
