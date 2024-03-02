package com.ke.institutions.service;

import com.ke.institutions.Dto.InstitutionDto;
import com.ke.institutions.Exceptions.DuplicateCourseException;
import com.ke.institutions.Exceptions.DuplicateInstitutionException;
import com.ke.institutions.Exceptions.InstitutionNotFoundException;
import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.respository.CourseRepository;
import com.ke.institutions.respository.InstitutionRepository;
import com.ke.institutions.restApi.InstitutionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository, CourseRepository courseRepository) {
        this.institutionRepository = institutionRepository;
        this.courseRepository = courseRepository;
    }

    // CRUD DB operations

    public Institution createInstitution(Institution institution) throws DuplicateInstitutionException {
        if (institutionRepository.findByName(institution.getName()).isPresent()) {
            throw new DuplicateInstitutionException("An institution with the same name already exists");
        }
        return institutionRepository.save(institution);
    }

    public List<Institution> searchInstitutionsByName(String name) {
        return institutionRepository.findByNameContainingIgnoreCase(name);
    }


    public Institution getInstitution(Long id) {
        return institutionRepository.findById(id).orElse(null);
    }

    public List<Institution> getAllInstitutions() {
        return institutionRepository.findAll();
    }

    public Institution updateInstitution(Institution institution) {
        return institutionRepository.save(institution);
    }

    public void deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
    }

//    public List<Institution> searchInstitutionsById(Long id) {
//        return institutionRepository.findById(getInstitution(id));
//    }

    public List<Institution> getAllInstitutionsSortedByName() {
        // Fetch all institutions from the repository and sort them by name in ascending order
        return institutionRepository.findAll()
                .stream()
                .sorted((i1, i2) -> i1.getName().compareToIgnoreCase(i2.getName()))
                .collect(Collectors.toList());
    }

    public Optional<Institution> findById(Long id) {
        return institutionRepository.findById(id);
    }

    public Optional<Institution> findByName(String name) {
        return institutionRepository.findByName(name);
    }
    public Institution editInstitutionName(Long id, String name) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new InstitutionNotFoundException("Institution not found with ID: " + id));

        // Check if the new name already exists
        if (institutionRepository.existsByNameAndIdNot(name, id)) {
            throw new DuplicateInstitutionException("An institution with the name '" + name + "' already exists.");
        }

        institution.setName(name);
        return institutionRepository.save(institution);
    }


    public Course createCourse(Long institutionId, Course course) {
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionNotFoundException("Institution not found with id: " + institutionId));

        // Check if the course name is null or empty
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be null or empty.");
        }

        // Check if a course with the same name already exists in the institution
        Course finalCourse = course;
        boolean courseExists = institution.getCourses().stream()
                .anyMatch(existingCourse -> finalCourse.getName().equals(existingCourse.getName()));

        if (courseExists) {
            throw new DuplicateCourseException("A course with the same name already exists in this institution.");
        }

        // Set the institution for the course
        course.setInstitution(institution);

        // Save the course first to ensure it has an ID generated
        course = courseRepository.save(course);

        // Add the course to the institution's courses collection
        institution.getCourses().add(course);
        institutionRepository.save(institution);

        return course;
    }

    public List<Course> getAllCoursesByInstitution(Long institutionId) {
        Institution institution = institutionRepository.findById(institutionId)
                .orElseThrow(() -> new InstitutionNotFoundException("Institution not found with id: " + institutionId));

        return new ArrayList<>(institution.getCourses());
    }
}
