package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.ServiceLayer.AdminCourseService;
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
    public ResponseEntity<String>CreateCourse(@RequestBody CourseDTO courseDTO){
        adminCourseService.CreateCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
    }
    public ResponseEntity<String>DeleteCourse(@PathVariable int CourseId){
        adminCourseService.DeleteCourse(CourseId);
        return ResponseEntity.ok("Course deleted successfully.");


    }
}
