package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long> {
    Optional<EmploymentType> findByName(String name);
    boolean existsByName(String name);
}