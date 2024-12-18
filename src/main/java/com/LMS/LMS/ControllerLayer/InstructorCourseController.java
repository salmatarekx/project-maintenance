package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.CourseDTO;
import com.LMS.LMS.DTO.UserRegistration;
import com.LMS.LMS.ModelLayer.*;
import com.LMS.LMS.RepositoryLayer.CourseRepository;
import com.LMS.LMS.ServiceLayer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/InstructorCourse")
public class InstructorCourseController {
    private final InstructorCourseService instructorCourseService;
    private  final UserService userService;
    private final CourseService courseService;

    public InstructorCourseController(InstructorCourseService instructorCourseService, UserService userService,CourseService courseService) {
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
    public ResponseEntity<String>RemoveStudentFromCourse(@PathVariable Long CourseId , @PathVariable Long StudentId)
    {
        instructorCourseService.removeStudentfromCourse(CourseId,StudentId);
        return ResponseEntity.ok("Student removed successfully from course.");

    }

    @Autowired
    private TrackingPerformanceService performanceTrackingService;

    @GetMapping("/getStudentPerformance/{studentId}/{courseId}")
    public ResponseEntity<Map<String, Object>> getStudentPerformance(
            @PathVariable Long studentId,@PathVariable Long courseId ) {


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

    @Autowired
    private QuizService quizService;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/createQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Map<String, Object> requestData) {
        try {
            // Extract details from the request body
            String title = (String) requestData.get("title");
            String description = (String) requestData.get("description");
            LocalDateTime startTime = LocalDateTime.parse((String) requestData.get("startTime"));
            LocalDateTime endTime = LocalDateTime.parse((String) requestData.get("endTime"));
            Long maxAttempts = Long.valueOf((Integer) requestData.get("maxAttempts"));
            Long timeLimit = Long.valueOf((Integer) requestData.get("timeLimit"));
            Long maxScore = Long.valueOf((Integer) requestData.get("maxScore"));
            Long courseId = Long.valueOf((Integer) requestData.get("courseId"));

            // Fetch course from repository
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setDescription(description);
            quiz.setStartTime(startTime);
            quiz.setEndTime(endTime);
            quiz.setMaxAttempts(maxAttempts);
            quiz.setMaxScore(maxScore);
            quiz.setCourse(course);
            quiz.setTimeLimit(timeLimit);

            Quiz createdQuiz = quizService.createQuiz(quiz);

            return ResponseEntity.ok(createdQuiz);
        } catch (Exception e) {
            // Handle exceptions gracefully
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("/getAllQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/startQuiz/{id}")
    public ResponseEntity<QuizGrades> startQuiz(
            @PathVariable Long id,
            @RequestParam Long studentId) {
        QuizGrades attempt = quizService.startQuiz(id, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @Autowired
    private AssignmentService assignmentService;
    @PostMapping("/createAssignment")
    public ResponseEntity<Assignment> createAssignment(@RequestBody Map<String, Object> requestData) {
        try {

            String title = (String) requestData.get("title");
            String description = (String) requestData.get("description");
            LocalDateTime dueDate = LocalDateTime.parse((String) requestData.get("dueDate"));
            Long maxScore = Long.valueOf((Integer) requestData.get("maxScore"));
            Long courseId = Long.valueOf((Integer) requestData.get("courseId"));


            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));

            // Create and populate assignment
            Assignment assignment = new Assignment();
            assignment.setTitle(title);
            assignment.setDescription(description);
            assignment.setDueDate(dueDate);
            assignment.setMaxScore(maxScore);
            assignment.setCourse(course);

            // Save assignment
            Assignment createdAssignment = assignmentService.createAssignment(assignment);

            return ResponseEntity.ok(createdAssignment);
        } catch (Exception e) {
            // Handle any exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("/getAssignment/{id}")
    public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) {
        return assignmentService.getAssignment(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAllAssignments")
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @Autowired
    private StudentQuizAssignmentService studentQuizAssignmentService;

    @GetMapping("/getAssignmentGrade/{assignmentId}")
    public ResponseEntity<List<AssignmentGrades>> viewAssignmentGrade(
            @PathVariable Long assignmentId) {
        List<AssignmentGrades> grades = studentQuizAssignmentService.viewAssignmentGrade(assignmentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/getQuizGrades/{quizId}")
    public ResponseEntity<List<QuizGrades>> viewQuizGrade(
            @PathVariable Long quizId) {
       List<QuizGrades> grades = studentQuizAssignmentService.viewQuizGrade(quizId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }



}