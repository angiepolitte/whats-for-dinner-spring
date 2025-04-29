package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.dto.LoginRequestDTO;
import com.angie.whats_for_dinner.dto.RegistrationRequestDTO;
import com.angie.whats_for_dinner.models.User;
import com.angie.whats_for_dinner.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        User user = userService.registerUser(registrationRequestDTO.getUsername(), registrationRequestDTO.getPassword());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "User registered successfully!");
        response.put("userId", user.getId()); // Ensure userId is here

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession session) {
        boolean authenticated = userService.authenticateUser(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        if (authenticated) {
            Optional<User> userOptional = userRepository.findByUsername(loginRequestDTO.getUsername());

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                session.setAttribute("user", loginRequestDTO.getUsername());
                session.setAttribute("userId", user.getId());

                Map<String, Object> response = new HashMap<>(); // Use Object for mixed types
                response.put("status", "ok");
                response.put("message", "Login successful");
                response.put("userId", user.getId()); // Ensure userId is here

                return ResponseEntity.ok(response);
            }
        }

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid credentials");
        return ResponseEntity.status(401).body(errorResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkLogin(HttpSession session) {
        String username = (String) session.getAttribute("user");
        Integer userId = (Integer) session.getAttribute("userId");

        if (username != null && userId != null) {
            return ResponseEntity.ok("Logged in as " + username + " (userId: " + userId + ")");
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not logged in");
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = userOptional.get();

        // Return only what you want the frontend to see
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
}