package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.loginRequest;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {
@Autowired
 private UserService userservice;
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        return userservice.register(req);
    }

    @PostMapping("/login")
    public String login(@RequestBody loginRequest req) {
        return userservice.login(req);
    }
}