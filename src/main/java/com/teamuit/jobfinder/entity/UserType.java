package com.teamuit.jobfinder.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}