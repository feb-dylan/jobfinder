package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JobApplicationStatusRepository extends JpaRepository<JobApplicationStatus, Long> {
    Optional<JobApplicationStatus> findByName(String name);
    boolean existsByName(String name);
}