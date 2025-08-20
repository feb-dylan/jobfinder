package com.teamuit.jobfinder.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class ProfileDTOs {

    @Data
    public static class JobSeekerProfileRequest {
        @NotBlank
        private String fullName;

        private String phoneNumber;

        private String resumeUrl;

        private String location;
    }

    @Data
    public static class EmployerProfileRequest {
        @NotBlank
        private String companyName;

        private String companyWebsite;

        private String description;

        private String location;
    }

    @Data
    public static class ProfileResponse {
        private Long id;
        private String email;
        private String userType;
        private Object profileData; // Could be JobSeeker or Employer profile
    }
}