package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobApplicationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationNoteRepository extends JpaRepository<JobApplicationNote, Long> {
    List<JobApplicationNote> findByApplicationId(Long applicationId);
    List<JobApplicationNote> findByCreatedById(Long createdById);
    List<JobApplicationNote> findByApplicationIdOrderByCreatedAtDesc(Long applicationId);
}
