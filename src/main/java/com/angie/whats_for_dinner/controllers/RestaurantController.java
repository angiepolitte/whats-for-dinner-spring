package com.angie.whats_for_dinner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @GetMapping
    public List<Restaurant> getRestaurants(@RequestParam String location, @RequestParam String cuisine) {
        // Query the database or an external API (Yelp/Google Places) for restaurant data
        return restaurantService.getRestaurants(location, cuisine);
    }
}
