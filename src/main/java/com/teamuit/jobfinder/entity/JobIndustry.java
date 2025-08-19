package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "job_industry")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobIndustry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}