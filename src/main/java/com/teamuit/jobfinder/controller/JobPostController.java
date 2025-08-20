package com.teamuit.jobfinder.controller;

import com.teamuit.jobfinder.dto.JobPostDTOs;
import com.teamuit.jobfinder.entity.JobPost;
import com.teamuit.jobfinder.entity.User;
import com.teamuit.jobfinder.service.JobPostService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobs")
public class JobPostController {

    private final JobPostService jobPostService;

    public JobPostController(JobPostService jobPostService) {
        this.jobPostService = jobPostService;
    }

    @PostMapping
    public ResponseEntity<JobPostDTOs.Response> createJobPost(
            @Valid @RequestBody JobPostDTOs.CreateRequest request,
            @AuthenticationPrincipal User user) {
        // Get employer profile ID from user
        Long employerId = getEmployerIdFromUser(user);

        JobPost jobPost = jobPostService.createJobPost(request, employerId, user.getId());
        JobPostDTOs.Response response = convertToDTO(jobPost);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<JobPostDTOs.Response>> getJobPosts(
            @Valid JobPostDTOs.FilterRequest filters) {
        Page<JobPostDTOs.Response> response = jobPostService.getJobPosts(filters);
        return ResponseEntity.ok(response);
    }

    // Helper methods
    private Long getEmployerIdFromUser(User user) {
        // Implementation to get employer profile ID from user
        return 1L; // Placeholder
    }

    private JobPostDTOs.Response convertToDTO(JobPost jobPost) {
        // Implementation to convert entity to DTO
        return new JobPostDTOs.Response(); // Placeholder
    }
}