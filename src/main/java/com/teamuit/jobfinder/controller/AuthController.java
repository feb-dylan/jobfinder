package com.teamuit.jobfinder.controller;

import com.teamuit.jobfinder.dto.AuthDTOs;
import com.teamuit.jobfinder.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDTOs.AuthResponse> register(
            @Valid @RequestBody AuthDTOs.RegisterRequest request) {
        try {
            AuthDTOs.AuthResponse response = authService.register(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new AuthDTOs.AuthResponse(null , null , null , null ,e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDTOs.AuthResponse> login(
            @Valid @RequestBody AuthDTOs.LoginRequest request) {
        try {
            AuthDTOs.AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new AuthDTOs.AuthResponse(null, null, null, null, e.getMessage()));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthDTOs.AuthResponse> refreshToken(
            @RequestBody AuthDTOs.RefreshTokenRequest request) {
        try {
            AuthDTOs.AuthResponse response = authService.refreshToken(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new AuthDTOs.AuthResponse(null, null, null, null, e.getMessage()));
        }
    }
}