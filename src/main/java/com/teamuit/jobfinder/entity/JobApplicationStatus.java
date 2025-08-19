package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_application_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplicationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}