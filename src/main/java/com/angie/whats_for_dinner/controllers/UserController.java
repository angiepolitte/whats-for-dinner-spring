package com.angie.whats_for_dinner.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/favorites")
    public List<Restaurant> getUserFavorites() {
        // Assuming user is logged in and you fetch their favorite restaurants
        return userService.getUserFavorites();
    }
}
