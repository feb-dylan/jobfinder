package com.teamuit.jobfinder.service;

import com.teamuit.jobfinder.dto.JobPostDTOs;
import com.teamuit.jobfinder.entity.*;
import com.teamuit.jobfinder.repo.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobPostService {

    private final JobPostRepository jobPostRepository;
    private final EmployerProfileRepository employerProfileRepository;
    private final JobIndustryRepository jobIndustryRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final JobPostStatusRepository jobPostStatusRepository;
    private final UserRepository userRepository;

    public JobPostService(JobPostRepository jobPostRepository,
                          EmployerProfileRepository employerProfileRepository,
                          JobIndustryRepository jobIndustryRepository,
                          EmploymentTypeRepository employmentTypeRepository,
                          JobPostStatusRepository jobPostStatusRepository,
                          UserRepository userRepository) {
        this.jobPostRepository = jobPostRepository;
        this.employerProfileRepository = employerProfileRepository;
        this.jobIndustryRepository = jobIndustryRepository;
        this.employmentTypeRepository = employmentTypeRepository;
        this.jobPostStatusRepository = jobPostStatusRepository;
        this.userRepository = userRepository;
    }

    public JobPost createJobPost(JobPostDTOs.CreateRequest request, Long employerId, Long userId) {
        // Get employer profile
        EmployerProfile employer = employerProfileRepository.findById(employerId)
                .orElseThrow(() -> new RuntimeException("Employer not found"));

        // Get related entities
        JobIndustry industry = jobIndustryRepository.findById(request.getIndustryId())
                .orElseThrow(() -> new RuntimeException("Industry not found"));

        EmploymentType employmentType = employmentTypeRepository.findById(request.getEmploymentTypeId())
                .orElseThrow(() -> new RuntimeException("Employment type not found"));

        JobPostStatus status = jobPostStatusRepository.findByName("Active")
                .orElseThrow(() -> new RuntimeException("Status not found"));

        User createdBy = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create job post
        JobPost jobPost = new JobPost();
        jobPost.setEmployer(employer);
        jobPost.setTitle(request.getTitle());
        jobPost.setDescription(request.getDescription());
        jobPost.setIndustry(industry);
        jobPost.setEmploymentType(employmentType);
        jobPost.setStatus(status);
        jobPost.setLocation(request.getLocation());
        jobPost.setSalaryMin(request.getSalaryMin());
        jobPost.setSalaryMax(request.getSalaryMax());
        jobPost.setCreatedBy(createdBy);
        jobPost.setCreatedAt(LocalDateTime.now());

        return jobPostRepository.save(jobPost);
    }

    public Page<JobPostDTOs.Response> getJobPosts(JobPostDTOs.FilterRequest filters) {
        Pageable pageable = PageRequest.of(filters.getPage(), filters.getSize(),
                Sort.by(Sort.Direction.DESC, "createdAt"));

        // Use the custom repository for filtering
        // This would be implemented in JobPostCustomRepositoryImpl
        Page<JobPost> jobPosts = jobPostRepository.findWithFilters(
                filters.getIndustryId() != null ? List.of(filters.getIndustryId()) : null,
                filters.getEmploymentTypeId() != null ? List.of(filters.getEmploymentTypeId()) : null,
                filters.getMinSalary() != null ? filters.getMinSalary().doubleValue() : null,
                filters.getMaxSalary() != null ? filters.getMaxSalary().doubleValue() : null,
                filters.getLocation(),
                filters.getSearch(),
                pageable
        );

        // Convert to DTOs
        return jobPosts.map(this::convertToDTO);
    }

    private JobPostDTOs.Response convertToDTO(JobPost jobPost) {
        JobPostDTOs.Response dto = new JobPostDTOs.Response();
        dto.setId(jobPost.getId());
        dto.setTitle(jobPost.getTitle());
        dto.setDescription(jobPost.getDescription());
        dto.setIndustry(jobPost.getIndustry().getName());
        dto.setEmploymentType(jobPost.getEmploymentType().getName());
        dto.setLocation(jobPost.getLocation());
        dto.setSalaryMin(jobPost.getSalaryMin());
        dto.setSalaryMax(jobPost.getSalaryMax());
        dto.setCompanyName(jobPost.getEmployer().getCompanyName());
        dto.setCreatedAt(jobPost.getCreatedAt().toString());

        return dto;
    }
}