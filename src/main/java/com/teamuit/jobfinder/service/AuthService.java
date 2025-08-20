package com.teamuit.jobfinder.service;

import com.teamuit.jobfinder.dto.AuthDTOs;
import com.teamuit.jobfinder.entity.User;
import com.teamuit.jobfinder.entity.UserType;
import com.teamuit.jobfinder.repo.UserRepository;
import com.teamuit.jobfinder.repo.UserTypeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, UserTypeRepository userTypeRepository,
                       PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthDTOs.AuthResponse register(AuthDTOs.RegisterRequest request) {
        // Check if user already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists with this email");
        }

        // Find user type
        UserType userType = userTypeRepository.findByName(request.getUserType())
                .orElseThrow(() -> new RuntimeException("Invalid user type"));

        // Create user
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUserType(userType);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        // Generate token
        String token = jwtService.generateToken(savedUser);

        // Prepare response
        AuthDTOs.AuthResponse response = new AuthDTOs.AuthResponse(e.getMessage());
        response.setToken(token);
        response.setEmail(savedUser.getEmail());
        response.setUserType(savedUser.getUserType().getName());

        return response;
    }

    public AuthDTOs.AuthResponse login(AuthDTOs.LoginRequest request) {
        // Find user
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate token
        String token = jwtService.generateToken(user);

        // Prepare response
        AuthDTOs.AuthResponse response = new AuthDTOs.AuthResponse(e.getMessage());
        response.setToken(token);
        response.setEmail(user.getEmail());
        response.setUserType(user.getUserType().getName());

        // Set profile ID based on user type
        if ("job_seeker".equals(user.getUserType().getName())) {
            // Get job seeker profile ID
        } else if ("employer".equals(user.getUserType().getName())) {
            // Get employer profile ID
        }

        return response;
    }
}