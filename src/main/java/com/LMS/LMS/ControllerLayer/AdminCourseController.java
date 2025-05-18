package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.AdminCourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/AdminCourse")
public class AdminCourseController {

    private final AdminCourseService adminCourseService;

    public AdminCourseController(AdminCourseService adminCourseService) {
        this.adminCourseService = adminCourseService;
    }

    @PostMapping("/CreateCourse")
    public ResponseEntity<String> createCourse(@Valid @RequestBody CourseDTO courseDTO,
                                               @RequestAttribute("currentUser") User currentUser) {
        adminCourseService.createCourse(courseDTO, currentUser);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
    }

    @DeleteMapping("/DeleteCourse/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId,
                                               @RequestAttribute("currentUser") User currentUser) {
        adminCourseService.deleteCourse(courseId, currentUser);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
