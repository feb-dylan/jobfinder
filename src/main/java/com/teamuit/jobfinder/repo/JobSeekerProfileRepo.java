package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JobSeekerProfileRepo extends JpaRepository<JobSeekerProfile, Long> {
    Optional<JobSeekerProfile> findByUserId(Long userId);
    Optional<JobSeekerProfile> findByUserEmail(String email);
    boolean existsByUserId(Long userId);
}