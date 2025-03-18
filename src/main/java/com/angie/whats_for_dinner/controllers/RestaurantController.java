package com.angie.whats_for_dinner.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Value("${google.api.key}")
    private String apiKey;

    private final WebClient webClient;

    public RestaurantController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://maps.googleapis.com/maps/api/place/textsearch/json").build();
    }

    @GetMapping
    public Mono<String> searchRestaurants(@RequestParam String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", "restaurants " + query) // Prepend "restaurants" to the query
                        .queryParam("key", apiKey)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}































//
//import com.angie.whats_for_dinner.models.Restaurant;
//import com.angie.whats_for_dinner.services.RestaurantService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/restaurants")
//public class RestaurantController {
//
//    @Autowired
//    private final RestaurantService restaurantService;
//
//    public RestaurantController(RestaurantService restaurantService) {
//        this.restaurantService = restaurantService;
//    }
//
//    @GetMapping
//    public List<Restaurant> getRestaurants(@RequestParam String location, @RequestParam String cuisine) {
//        // Query the service to get restaurant data
//        return restaurantService.getRestaurants(location, cuisine);
//    }
//}
