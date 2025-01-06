package com.example.soba.service;

import com.example.soba.entity.User;

public interface UserService {
    User signUp(User user);
    User login(String username, String password);
    boolean usernameExists(String username);
    boolean emailExists(String email);
}
