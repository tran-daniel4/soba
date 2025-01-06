package com.example.soba.service.impl;

import com.example.soba.entity.User;
import com.example.soba.repository.UserRepository;
import com.example.soba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(User user) {
        if (userRepository.usernameExists(user.getUsername())) {
            throw new RuntimeException("Username is already taken");
        } else if (userRepository.emailExists(user.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    // why do we get User here instead of String username, etc.. why do we save only password

    @Override
    public User login(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else {
                throw new RuntimeException("Invalid password");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.usernameExists(username);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }
    // how does the flow go from here to userrepo?
}
