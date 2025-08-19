package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employer_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_website")
    private String companyWebsite;

    @Column(name = "description")
    private String description;

    @Column(name = "location")
    private String location;
}