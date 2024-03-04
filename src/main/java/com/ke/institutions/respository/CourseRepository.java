package com.ke.institutions.respository;

import com.ke.institutions.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByNameContainingIgnoreCase(String keyword);

    boolean existsByNameAndInstitutionId(String name, Long id);
}
