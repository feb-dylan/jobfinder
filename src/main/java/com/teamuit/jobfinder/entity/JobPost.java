package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private EmployerProfile employer;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private JobIndustry industry;

    @ManyToOne
    @JoinColumn(name = "employment_type_id")
    private EmploymentType employmentType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private JobPostStatus status;

    @Column(name = "location")
    private String location;

    @Column(name = "salary_min")
    private BigDecimal salaryMin;

    @Column(name = "salary_max")
    private BigDecimal salaryMax;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}