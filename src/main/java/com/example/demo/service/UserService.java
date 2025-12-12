package com.example.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.loginRequest;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil; // Inferred import

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class UserService {
@Autowired
   private UserRepository userRepository;
@Autowired
  private  PasswordEncoder passwordEncoder;
    // -----
@Autowired
  private  JwtUtil jwtUtil;

    public String register(RegisterRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            return "Email already registered!";
        }

        UserModel user = UserModel.builder()
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .username(req.getUsername())
                .phone(req.getPhone())
                .build();

        userRepository.save(user);

        return "Registered Successfully!";
    }

    public String login(loginRequest req) {

        UserModel user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Email"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}