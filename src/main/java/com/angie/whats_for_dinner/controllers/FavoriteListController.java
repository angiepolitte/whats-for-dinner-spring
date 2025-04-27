package com.angie.whats_for_dinner.controllers;


import com.angie.whats_for_dinner.data.FavoriteListRepository;
import com.angie.whats_for_dinner.data.FavoriteRestaurantRepository;
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

    @GetMapping("/all-lists")
    public ResponseEntity<List<FavoriteList>> getAllFavoriteLists() {
        List<FavoriteList> lists = favoriteListService.getAllFavoriteLists();
        return ResponseEntity.ok(lists);
    }

    @PostMapping("/create")
    public ResponseEntity<FavoriteList> createFavoriteList(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        Long userId = ((Number) payload.get("userId")).longValue();
        FavoriteList newList = favoriteListService.createFavoriteList(name, userId);
        return ResponseEntity.ok(newList);
    }

    @PostMapping("/{listId}/add-restaurant/{restaurantId}")
    public ResponseEntity<Void> addRestaurantToList(@PathVariable Long listId, @PathVariable Long restaurantId) {
        favoriteListService.addRestaurantToList(listId, restaurantId, favoriteRestaurantRepository);
        return ResponseEntity.ok().build();
    }
}

//@RestController
//@RequestMapping("/api/favorite-lists")
//@CrossOrigin(origins = "http://localhost:5173") // Adjust if needed
//public class FavoriteListController {
//
//    @Autowired
//    private FavoriteListService favoriteListService;
//
//    @PostMapping("/create")
//    public ResponseEntity<FavoriteList> createFavoriteList(@RequestBody Map<String, String> requestData) {
//        String listName = requestData.get("name");
//        Long userId = Long.parseLong(requestData.get("userId"));
//
//        FavoriteList newList = favoriteListService.createFavoriteList(listName, userId);
//        return ResponseEntity.ok(newList);
//    }
//}

//@RestController
//@RequestMapping("/api/favorite-lists")
//public class FavoriteListController {
//
//    @Autowired
//    private FavoriteListRepository favoriteListRepository;
//
//    @PostMapping
//    public FavoriteList createFavoriteList(@RequestBody FavoriteList favoriteList) {
//        // Save the new favorite list to the database
//        return favoriteListRepository.save(favoriteList);
//    }
//
//    @GetMapping("/user/{userId}")
//    public List<FavoriteList> getFavoriteListsByUser(@PathVariable Long userId) {
//        return favoriteListRepository.findByUser(userId);
//    }
//}
