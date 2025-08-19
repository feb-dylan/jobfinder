package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobIndustry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface JobIndustryRepository extends JpaRepository<JobIndustry, Long> {
    Optional<JobIndustry> findByName(String name);
    boolean existsByName(String name);
}