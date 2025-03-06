package com.angie.whats_for_dinner.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS can be enabled globally like this
@Configuration
public class AppConfig implements WebMvcConfigurer { // Implement WebMvcConfigurer

    // Bean for RestTemplate (already present)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Global CORS configuration
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Apply to all API routes
                .allowedOrigins("http://localhost:5173") // React frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow these methods
                .allowCredentials(true); // If you need cookies/auth
    }
}

//        WebMvcConfigurer Interface: By implementing WebMvcConfigurer, you can define global configurations like CORS in a centralized place.
//        This eliminates the need to add @CrossOrigin annotations to each controller.
//Global CORS Settings: The addCorsMappings() method ensures that all routes under /api/**
// (or other route patterns you specify) are accessible from your React frontend at http://localhost:5173.
// You can change the frontend URL later,
// and this configuration will automatically update for all your APIs.
