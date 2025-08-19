package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobSeekerId(Long jobSeekerId);
    List<JobApplication> findByJobPostId(Long jobPostId);
    List<JobApplication> findByStatusId(Long statusId);
    Optional<JobApplication> findByJobSeekerIdAndJobPostId(Long jobSeekerId, Long jobPostId);
    boolean existsByJobSeekerIdAndJobPostId(Long jobSeekerId, Long jobPostId);
    int countByJobPostId(Long jobPostId);
}