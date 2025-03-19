package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Enable CORS for this controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String confirmPassword) {

    // Check if the username is already taken
    if (userRepository.findByUsername(username).isPresent()) {
        return ResponseEntity.badRequest().body("Username already exists.");
    }

    // Check if passwords match
    if (!password.equals(confirmPassword)) {
        return ResponseEntity.badRequest().body("Passwords do not match.");
    }

    // Validate password strength (at least 8 chars, 1 letter, 1 number, 1 special char)
    if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
        return ResponseEntity.badRequest().body(
                "Password must be at least 8 characters long and contain at least one letter, one number, and one special character.");
    }


            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(passwordEncoder.encode(password));  // Ensure password is encoded
            userRepository.save(newUser);  // Save the encoded password to the database


    return ResponseEntity.ok("User registered successfully.");
}

}
