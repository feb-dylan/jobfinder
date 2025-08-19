package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_seeker_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeekerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "resume_url")
    private String resumeUrl;

    @Column(name = "location")
    private String location;
}