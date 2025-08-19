package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_application")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeekerProfile jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    @Column(name = "cover_letter_url")
    private String coverLetterUrl;

    @Column(name = "resume_url")
    private String resumeUrl;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private JobApplicationStatus status;

    @Column(name = "applied_at")
    private LocalDateTime appliedAt;
}