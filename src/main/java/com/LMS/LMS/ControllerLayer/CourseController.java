    package com.LMS.LMS.ControllerLayer;

    import com.LMS.LMS.DTO.CourseDTO;
    import com.LMS.LMS.ModelLayer.Course;
    import com.LMS.LMS.ModelLayer.User;
    import com.LMS.LMS.ServiceLayer.CourseService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.annotation.Lazy;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/Courses")
    public class CourseController {
        private final CourseService courseService;

        @Autowired
        public CourseController(@Lazy CourseService courseService) {
            this.courseService = courseService;
        }

        @PostMapping("CreateCourse")
        public ResponseEntity<String> createCourse(@RequestBody CourseDTO courseDTO, @RequestAttribute User currentUser) {
            courseService.createCourse(courseDTO, currentUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully.");
        }


        @GetMapping
        public ResponseEntity<List<Course>> getAllCourses() {
            return ResponseEntity.ok(courseService.getAllCourses());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
            return ResponseEntity.ok(courseService.getCourseById(id));
        }

        @PutMapping("/{id}")
        public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseDTO courseDTO, @RequestAttribute User currentUser) {
            courseService.updateCourse(id, courseDTO, currentUser);
            return ResponseEntity.ok("Course updated successfully.");
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteCourse(@PathVariable Long id, @RequestAttribute User currentUser) {
            courseService.deleteCourse(id, currentUser);
            return ResponseEntity.ok("Course deleted successfully.");
        }

        @PostMapping("/{courseId}/enroll")
        public ResponseEntity<String> enrollStudent(@PathVariable Long courseId, @RequestParam Long studentId) {
            courseService.enrollStudent(courseId, studentId);
            return ResponseEntity.ok("Student enrolled successfully.");
        }

        @GetMapping("/{courseId}/students")
        public ResponseEntity<List<User>> getEnrolledStudents(@PathVariable Long courseId) {
            return ResponseEntity.ok(courseService.getEnrolledStudents(courseId));
        }
    }
