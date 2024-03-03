package com.ke.institutions.service;

import com.ke.institutions.entity.Course;
import com.ke.institutions.respository.CourseRepository;
import com.ke.institutions.respository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
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


    public List<Course> sortCoursesByName(List<Course> courses, boolean ascending) {
        Comparator<Course> nameComparator = Comparator.comparing(Course::getName);

        // use the natural order to sort in ascending and the reverse order to sort in descending

        if(!ascending) {
            nameComparator = nameComparator.reversed();
        }

        courses.sort(nameComparator);

        return courses;
    }

    public List<Course> filterCourseByName(String keyword) {
        return courseRepository.findByNameContainingIgnoreCase(keyword);
    }


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
