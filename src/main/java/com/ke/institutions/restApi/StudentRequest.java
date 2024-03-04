package com.ke.institutions.restApi;

import com.ke.institutions.entity.Course;
import com.ke.institutions.entity.Student;
import com.ke.institutions.service.CourseService;
import com.ke.institutions.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/students")
public class StudentRequest {

    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public StudentRequest (StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @PostMapping("/{courseId}/students")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable Long courseId, @RequestBody List<Student> students) {
        return ResponseEntity.ok(courseService.addStudentsToCourse(courseId, students));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student !=null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return ResponseEntity.ok(studentService.updateStudent(student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
