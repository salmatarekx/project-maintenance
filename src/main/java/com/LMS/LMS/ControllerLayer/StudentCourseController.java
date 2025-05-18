package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ServiceLayer.CourseService;
import com.LMS.LMS.ServiceLayer.StudentCourseService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Student/Course")
@Validated // Enables validation on method parameters
public class StudentCourseController {

    private final StudentCourseService studentCourseService;
    private final CourseService courseService;

    @Autowired
    public StudentCourseController(@Lazy StudentCourseService studentCourseService,
                                   @Lazy CourseService courseService) {
        this.studentCourseService = studentCourseService;
        this.courseService = courseService;
    }

    @PostMapping("/{studentId}/Enroll/{courseId}")
    public ResponseEntity<String> enrollStudent(
            @PathVariable @NotNull(message = "Course ID is required") Long courseId,
            @PathVariable @NotNull(message = "Student ID is required") Long studentId) {

        courseService.enrollStudent(courseId, studentId);
        return ResponseEntity.ok("Student enrolled in course successfully.");
    }
}
