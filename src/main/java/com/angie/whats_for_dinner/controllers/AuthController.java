package com.angie.whats_for_dinner.controllers;

import com.angie.whats_for_dinner.dto.LoginRequestDTO;
import com.angie.whats_for_dinner.dto.RegistrationRequestDTO;
import com.angie.whats_for_dinner.models.User;
import com.angie.whats_for_dinner.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegistrationRequestDTO registrationRequestDTO) {
        // Optional: validate password matching or strength on backend side
        userService.registerUser(registrationRequestDTO.getUsername(), registrationRequestDTO.getPassword());

        Map<String, String> response = new HashMap<>();
        response.put("status", "ok");
        response.put("message", "User registered successfully!");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession session) {
        boolean authenticated = userService.authenticateUser(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());

        if (authenticated) {
            session.setAttribute("user", loginRequestDTO.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("status", "ok");
            response.put("message", "Login successful");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid credentials");
            return ResponseEntity.status(401).body(errorResponse);
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/check")
    public ResponseEntity<String> checkLogin(HttpSession session) {
        String username = (String) session.getAttribute("user");
        if (username != null) {
            return ResponseEntity.ok("Logged in as " + username);
        } else {
            return ResponseEntity.status(401).body("Not logged in");
        }
    }

}
