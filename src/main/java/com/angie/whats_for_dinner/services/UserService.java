package com.angie.whats_for_dinner.services;


import com.angie.whats_for_dinner.data.UserRepository;
import com.angie.whats_for_dinner.models.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String username, String rawPassword) {
        String encodedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());  // Use BCrypt directly
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public boolean authenticateUser(String username, String rawPassword) {
        // Use Optional to handle the potential null value safely
        Optional<User> userOptional = userRepository.findByUsername(username);

        // Check if the user exists
        if (userOptional.isEmpty()) {
            return false;  // Return false if the user doesn't exist
        }

        User user = userOptional.get();  // Get the User object from the Optional

        // Check the password using BCrypt
        return BCrypt.checkpw(rawPassword, user.getPassword());
    }
}

