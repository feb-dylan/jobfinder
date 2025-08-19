package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByName(String name);
}