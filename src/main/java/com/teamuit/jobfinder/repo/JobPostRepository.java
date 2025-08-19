package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long>, JpaSpecificationExecutor<JobPost> {
    List<JobPost> findByEmployerId(Long employerId);
    List<JobPost> findByStatusId(Long statusId);
    List<JobPost> findByIndustryId(Long industryId);
    List<JobPost> findByEmploymentTypeId(Long employmentTypeId);
    List<JobPost> findByLocationContainingIgnoreCase(String location);
    List<JobPost> findByTitleContainingIgnoreCase(String title);
    List<JobPost> findBySalaryMinGreaterThanEqualAndSalaryMaxLessThanEqual(Double minSalary, Double maxSalary);
}