package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_application_note")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplicationNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    private JobApplication application;

    @Column(name = "note_text")
    private String noteText;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}