package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.models.FavoriteList;
import com.angie.whats_for_dinner.models.User;
import com.angie.whats_for_dinner.data.UserRepository; // You might need this
import com.angie.whats_for_dinner.data.FavoriteListRepository; // To fetch user's lists
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteListRepository favoriteListRepository;
    @GetMapping("/info")
    public ResponseEntity<Map<String,String>> getCurrentUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null
                && auth.isAuthenticated()
                && auth.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal())
                    .getUsername();
            return ResponseEntity.ok(Map.of("username", username));
        }

        // ← instead of 401, return 200 with empty map
        return ResponseEntity.ok(Map.of());
    }

    @GetMapping("/favorite-lists")
    public ResponseEntity<List<FavoriteList>> getCurrentUserFavoriteLists() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (userOptional.isPresent()) {
                List<FavoriteList> favoriteLists = favoriteListRepository.findByUser(userOptional.get());
                return ResponseEntity.ok(favoriteLists);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(401).build(); // Unauthorized if not authenticated
    }
}
//    @GetMapping("/info")
//    public ResponseEntity<User> getCurrentUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
//            String username = ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername();
//            Optional<User> userOptional = userRepository.findByUsername(username);
//            return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        }
//        return ResponseEntity.status(401).build(); // Unauthorized if not authenticated
//    }



    // You can add more endpoints here for other user-related actions



