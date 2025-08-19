package com.teamuit.jobfinder.repo;

import com.teamuit.jobfinder.entity.JobPost;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JobPostCustomRepositoryImpl implements JobPostCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<JobPost> findWithFilters(List<Long> industryIds, List<Long> employmentTypeIds,
                                         Double minSalary, Double maxSalary, String location,
                                         String searchTerm, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<JobPost> query = cb.createQuery(JobPost.class);
        Root<JobPost> jobPost = query.from(JobPost.class);

        List<Predicate> predicates = new ArrayList<>();

        if (industryIds != null && !industryIds.isEmpty()) {
            predicates.add(jobPost.get("industry").get("id").in(industryIds));
        }

        if (employmentTypeIds != null && !employmentTypeIds.isEmpty()) {
            predicates.add(jobPost.get("employmentType").get("id").in(employmentTypeIds));
        }

        if (minSalary != null) {
            predicates.add(cb.greaterThanOrEqualTo(jobPost.get("salaryMin"), minSalary));
        }

        if (maxSalary != null) {
            predicates.add(cb.lessThanOrEqualTo(jobPost.get("salaryMax"), maxSalary));
        }

        if (location != null && !location.isEmpty()) {
            predicates.add(cb.like(cb.lower(jobPost.get("location")), "%" + location.toLowerCase() + "%"));
        }

        if (searchTerm != null && !searchTerm.isEmpty()) {
            Predicate titlePredicate = cb.like(cb.lower(jobPost.get("title")), "%" + searchTerm.toLowerCase() + "%");
            Predicate descriptionPredicate = cb.like(cb.lower(jobPost.get("description")), "%" + searchTerm.toLowerCase() + "%");
            predicates.add(cb.or(titlePredicate, descriptionPredicate));
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.desc(jobPost.get("createdAt")));

        List<JobPost> result = entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // Count query for pagination
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<JobPost> countRoot = countQuery.from(JobPost.class);
        countQuery.select(cb.count(countRoot)).where(predicates.toArray(new Predicate[0]));
        Long total = entityManager.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(result, pageable, total);
    }
}