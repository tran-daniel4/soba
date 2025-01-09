package com.example.soba.controller;

import com.example.soba.entity.User;
import com.example.soba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User signingUp = userService.signUp(user);
            return ResponseEntity.ok(signingUp);
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User loggingIn = userService.login(user);
            return ResponseEntity.ok(loggingIn);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }





}
