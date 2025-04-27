package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/current-user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // Return 401 if no user is logged in
        }
        return ResponseEntity.ok(user);  // Return the user as JSON
    }
}
