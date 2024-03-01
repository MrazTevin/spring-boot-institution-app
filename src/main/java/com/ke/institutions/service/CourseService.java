package com.ke.institutions.service;

import com.ke.institutions.Exceptions.DuplicateCourseException;
import com.ke.institutions.Exceptions.InstitutionNotFoundException;
import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.respository.CourseRepository;
import com.ke.institutions.respository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstitutionRepository institutionRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository, InstitutionRepository institutionRepository) {
        this.courseRepository = courseRepository;
        this.institutionRepository = institutionRepository;
    }

    // CRUD operations for courses
//    public Course createCourse(Long institutionId, Course course) {
//        return courseRepository.save(course);
//    }


//    public Course createCourse(Long institutionId, Course course) {
//        Institution institution = institutionRepository.findById(institutionId)
//                .orElseThrow(() -> new InstitutionNotFoundException("Institution not found with id: " + institutionId));
//
//        // Check if the course name is null or empty
//        if (course.getName() == null || course.getName().isEmpty()) {
//            throw new IllegalArgumentException("Course name cannot be null or empty.");
//        }
//
//        // Check if a course with the same name already exists in the institution
//        Course finalCourse = course;
//        boolean courseExists = institution.getCourses().stream()
//                .anyMatch(existingCourse -> finalCourse.getName().equals(existingCourse.getName()));
//
//        if (courseExists) {
//            throw new DuplicateCourseException("A course with the same name already exists in this institution.");
//        }
//
//        // Set the institution for the course
//        course.setInstitution(institution);
//
//        // Save the course first to ensure it has an ID generated
//        course = courseRepository.save(course);
//
//        // Add the course to the institution's courses collection
//        institution.getCourses().add(course);
//        institutionRepository.save(institution);
//
//        return course;
//    }


    public Course getCourse(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
 }
