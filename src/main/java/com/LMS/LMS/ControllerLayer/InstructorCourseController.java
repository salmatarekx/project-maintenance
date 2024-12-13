package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.InstructorCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("InstructorCourse")
public class InstructorCourseController {
    private final InstructorCourseService instructorCourseService;

    public InstructorCourseController(InstructorCourseService instructorCourseService) {
        this.instructorCourseService = instructorCourseService;
    }
    @PostMapping
    public ResponseEntity<String>CreateCourse(@RequestBody  CourseDTO courseDTO, @RequestAttribute User user){
        instructorCourseService.CreateCourse(courseDTO ,user);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");

    }
    @DeleteMapping("/courses/{CourseId}/students/{StudentId}")
    public ResponseEntity<String>RemoveStudentFromCourse(@PathVariable int CourseId , @PathVariable Long StudentId)
    {
      instructorCourseService.removeStudentfromCourse(CourseId,StudentId);
      return ResponseEntity.ok("Student removed successfully from course.");

    }
}
