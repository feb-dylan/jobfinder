package com.teamuit.jobfinder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;

public class JobPostDTOs {

    @Data
    public static class CreateRequest {
        @NotBlank
        private String title;

        private String description;

        @NotNull
        private Long industryId;

        @NotNull
        private Long employmentTypeId;

        private String location;

        @Positive
        private BigDecimal salaryMin;

        @Positive
        private BigDecimal salaryMax;
    }

    @Data
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private String industry;
        private String employmentType;
        private String location;
        private BigDecimal salaryMin;
        private BigDecimal salaryMax;
        private String companyName;
        private String createdAt;
    }

    @Data
    public static class FilterRequest {
        private Long industryId;
        private Long employmentTypeId;
        private BigDecimal minSalary;
        private BigDecimal maxSalary;
        private String location;
        private String search;
        private int page = 0;
        private int size = 10;
    }
}