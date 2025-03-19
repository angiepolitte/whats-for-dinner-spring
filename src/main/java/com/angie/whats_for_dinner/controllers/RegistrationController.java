package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // Change to @Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration"; // Return the name of the Thymeleaf template
    }

@PostMapping("/register")
public ResponseEntity<String> registerUser(
        @Valid @RequestParam String username,
        @Valid @RequestParam String password,
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

    // Save new user
    User newUser = new User();
    newUser.setUsername(username);
    newUser.setPassword(passwordEncoder.encode(password));
    userRepository.save(newUser);

    return ResponseEntity.ok("User registered successfully.");
}

}
