package com.angie.whats_for_dinner.controllers;


import com.angie.whats_for_dinner.data.FavoriteListRepository;
import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
import com.angie.whats_for_dinner.dto.FavoriteListDTO;
import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.services.FavoriteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/favorite-lists")
@CrossOrigin(origins = "http://localhost:5173")
public class FavoriteListController {

    @Autowired
    private FavoriteListService favoriteListService;

    @Autowired
    private FavoriteRestaurantRepository favoriteRestaurantRepository;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteList>> getAllFavoriteListsByUser(@PathVariable Long userId) {
        List<FavoriteList> lists = favoriteListService.getAllFavoriteListsByUserId(userId);
        return ResponseEntity.ok(lists);
    }

    @PostMapping("/create")
    public ResponseEntity<FavoriteList> createFavoriteList(@RequestBody FavoriteListDTO favoriteListDTO) {
        Long userId = favoriteListDTO.getUserId();
        String name = favoriteListDTO.getName();
        FavoriteList newList = favoriteListService.createFavoriteList(name, userId);
        return ResponseEntity.ok(newList);
    }


    @PostMapping("/{listId}/add-restaurant/{restaurantId}")
    public ResponseEntity<Void> addRestaurantToList(@PathVariable Long listId, @PathVariable Long restaurantId) {
        favoriteListService.addRestaurantToList(listId, restaurantId);
        return ResponseEntity.ok().build();
    }
}
