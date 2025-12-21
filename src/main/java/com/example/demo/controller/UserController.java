package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.WithGoogle;
import com.example.demo.dto.loginRequest;
import com.example.demo.service.GoogleAuthService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
@Autowired
 private UserService userservice;
@Autowired
private GoogleAuthService googleAuthService;
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return userservice.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody loginRequest req) {
        return userservice.login(req);
    }
    @PostMapping("/google")
    public String  googleLogin(@RequestBody WithGoogle req) {
    	
    	
    	return googleAuthService.loginWithGoogle(req.getIdToken());
    	
    }
}