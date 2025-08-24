package com.teamuit.jobfinder.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class AuthDTOs {

    @Data
    public static class RegisterRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        @Size(min = 6)
        private String password;

        @NotBlank
        private String userType; // "job_seeker" or "employer"
    }

    @Data
    public static class LoginRequest {
        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthResponse {
        private String token;
        private String email;
        private String userType;
        private Long profileId;
        private String message; // Added for error messages
        
        // Constructor for success responses (without message)
        public AuthResponse(String token, String email, String userType, Long profileId) {
            this.token = token;
            this.email = email;
            this.userType = userType;
            this.profileId = profileId;
        }
        
        // Constructor for error responses (with message only)
        public AuthResponse(String message) {
            this.message = message;
        }
    }
    
    @Data
    public static class RefreshTokenRequest {
        private String refreshToken;
    }
}