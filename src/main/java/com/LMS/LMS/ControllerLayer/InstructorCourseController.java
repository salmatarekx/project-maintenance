package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.Course;
import com.LMS.LMS.ModelLayer.User;
import com.LMS.LMS.ServiceLayer.CourseService;
import com.LMS.LMS.ServiceLayer.InstructorCourseService;
import com.LMS.LMS.ServiceLayer.TrackingPerformanceService;
import com.LMS.LMS.ServiceLayer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/InstructorCourse")
public class InstructorCourseController {
    private final InstructorCourseService instructorCourseService;
    private  final UserService userService;
    private final CourseService courseService;

    public InstructorCourseController(@Lazy InstructorCourseService instructorCourseService, @Lazy UserService userService,@Lazy CourseService courseService) {
        this.instructorCourseService = instructorCourseService;
        this.userService = userService;
        this.courseService = courseService;
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

    @Autowired
    private TrackingPerformanceService performanceTrackingService;

    @GetMapping("/performance")
    public ResponseEntity<Map<String, Object>> getStudentPerformance(
            @RequestParam Long courseId, @RequestParam Long studentId) {


        Course course = courseService.getAllCourses().stream()
                .filter(c -> (c.getId() == courseId))
                .findFirst().orElse(null);

        User student = userService.getUserById(studentId).orElse(null);

        if (course == null || student == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Map<String, Object> performanceData = performanceTrackingService.getSimplifiedPerformance(student, course);
        return ResponseEntity.ok(performanceData);
    }
}
