package com.ke.institutions.restApi;


import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Institution;
import com.ke.institutions.service.CourseService;
import com.ke.institutions.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/courses")
public class CourseRequest {
    private final CourseService courseService;
    private final InstitutionService institutionService;
    @Autowired
    public CourseRequest(CourseService courseService, InstitutionService institutionService) {
        this.courseService = courseService;
        this.institutionService = institutionService;
    }

    @GetMapping("/{institutionId}/sorted")
    public ResponseEntity<List<Course>> getAllCoursesSortedByName(
       @PathVariable Long institutionId,
       @RequestParam(required = false, defaultValue = "asc") String order) {

        boolean ascending = order.equalsIgnoreCase("asc");

        List<Course> courses = institutionService.getAllCoursesByInstitution(institutionId);

        List<Course> sortedCourses = courseService.sortCoursesByName(courses, ascending);

        return  ResponseEntity.ok(sortedCourses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> filterCoursesByName(@RequestParam String keyword) {
        List<Course> filteredCourse = courseService.filterCourseByName(keyword);
        return ResponseEntity.ok(filteredCourse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        Course course = courseService.getCourse(id);
        if (course !=null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        course.setId(id);
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }


}
