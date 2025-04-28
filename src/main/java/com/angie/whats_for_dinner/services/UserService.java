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
        User user = userRepository.findByUsername(username);
        if (user == null) return false;
        return BCrypt.checkpw(rawPassword, user.getPassword());  // Use BCrypt directly
    }
}

