package com.ke.institutions.service;

import com.ke.institutions.Exceptions.CourseNotFoundException;
import com.ke.institutions.Exceptions.DuplicateCourseException;
import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Student;
import com.ke.institutions.respository.CourseRepository;
import com.ke.institutions.respository.InstitutionRepository;
import com.ke.institutions.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
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

    public Course updateCourse(Long id, String newName) {
       Course course = courseRepository.findById(id)
               .orElseThrow(() -> new CourseNotFoundException("Course not found with id : " + id ));

       if (courseRepository.existsByNameAndInstitutionId(newName, course.getInstitution().getId())) {
           throw new DuplicateCourseException("A course with the name '" + newName + "' already exists in this institution.");
       }

       course.setName(newName);
       return courseRepository.save(course);
    }

    public Course addStudentsToCourse(Long courseId, List<Student> students) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + courseId));

        students.forEach(
                student -> {
                    student.setCourse(course);
                    studentRepository.save(student);
                }
        );
        course.getStudents().addAll(students);
        return courseRepository.save(course);
    }
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
 }
