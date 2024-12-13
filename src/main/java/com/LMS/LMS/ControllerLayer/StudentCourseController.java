package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ServiceLayer.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Student/Course")
public class StudentCourseController {
    private final StudentCourseService studentCourseService ;

    @Autowired
    public StudentCourseController(@Lazy StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }
    @PostMapping("/{StudentId}/Enorll/{CourseId}")
    public ResponseEntity<String>Enrollment(@PathVariable int CourseId , @PathVariable Long StudentId) {
        studentCourseService.Enroll(CourseId , StudentId);
        return ResponseEntity.ok("Student Enrolled in course Successfully.") ;
    }



}
