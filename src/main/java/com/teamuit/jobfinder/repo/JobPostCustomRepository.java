package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface JobPostCustomRepository {
    Page<JobPost> findWithFilters(List<Long> industryIds, List<Long> employmentTypeIds,
                                  Double minSalary, Double maxSalary, String location,
                                  String searchTerm, Pageable pageable);
}