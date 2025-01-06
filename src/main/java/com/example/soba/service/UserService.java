package com.example.soba.service;

import com.example.soba.entity.User;

public interface UserService {
    User signUp(User user);
    User login(String username, String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
