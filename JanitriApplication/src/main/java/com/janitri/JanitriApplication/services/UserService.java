package com.janitri.JanitriApplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janitri.JanitriApplication.models.User;
import com.janitri.JanitriApplication.repositories.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to register a user
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Method to validate user credentials
    public boolean validateUser(String email, String password) {
        User user = userRepository.findByEmail(email);  // The user will be found by email (String)
        return user != null && user.getPassword().equals(password);
    }

    // Method to find a user by email (String)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);  // Assuming your UserRepository has this method
    }
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);  // Fetch user by ID
    }
}

