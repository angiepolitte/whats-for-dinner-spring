//package com.angie.whats_for_dinner.services;
//
//import com.angie.whats_for_dinner.data.UserRepository;
//import com.angie.whats_for_dinner.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UserRepository userRepository;  // Inject your repository to fetch user details
//
//    public boolean isValidLogin(String username, String password) {
//        Optional<User> user = userRepository.findByUsername(username);  // Retrieve user by username
//        if (user.isPresent()) {
//            return password.equals(user.get().getPassword()); // Simple password comparison, ideally should be hashed
//        }
//        return false;
//    }
//}


//import com.angie.whats_for_dinner.models.Restaurant;
//import org.springframework.stereotype.Service;
//import java.util.List;
//
//@Service
//public class UserService {
//
////    public List<Restaurant> getUserFavorites() {
////        // Logic to fetch user's favorite restaurants, e.g., from the database
////        return List.of(); // Just a placeholder for now
////    }
//}

