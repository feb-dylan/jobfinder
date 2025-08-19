package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployerProfileRepository extends JpaRepository<EmployerProfile, Long> {
    Optional<EmployerProfile> findByUserId(Long userId);
    Optional<EmployerProfile> findByUserEmail(String email);
    boolean existsByUserId(Long userId);
    boolean existsByCompanyName(String companyName);
}