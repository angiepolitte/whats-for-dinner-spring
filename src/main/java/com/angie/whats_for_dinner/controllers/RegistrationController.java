package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

//    @PostMapping("/register") this is for thymeleaf
//    public String registerUser(@RequestParam String username, @RequestParam String password) {
//        if (userRepository.findByUsername(username).isPresent()) {
//            return "redirect:/registration?error"; // Redirect with error
//        }
@PostMapping("/register") //this is for react
public ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {
    if (userRepository.findByUsername(username).isPresent()) {
        return ResponseEntity.badRequest().body("Username already exists");
    }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully"); // Return JSON response for react
}
//        return "success-page"; // Return the success page for thymeleaf
    }
