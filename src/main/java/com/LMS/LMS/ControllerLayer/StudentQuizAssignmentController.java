package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ServiceLayer.QuizService;
import com.LMS.LMS.ServiceLayer.StudentQuizAssignmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/student")
@Validated
public class StudentQuizAssignmentController {

    private final StudentQuizAssignmentService studentQuizAssignmentService;
    private final QuizService quizService;

    public StudentQuizAssignmentController(StudentQuizAssignmentService studentQuizAssignmentService,
                                           QuizService quizService) {
        this.studentQuizAssignmentService = studentQuizAssignmentService;
        this.quizService = quizService;
    }

    @PostMapping("/quiz/{quizId}/take")
    public ResponseEntity<QuizGrades> takeQuiz(
            @PathVariable @NotNull Long quizId,
            @RequestParam @NotNull Long studentId) {
        QuizGrades attempt = studentQuizAssignmentService.takeQuiz(quizId, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/assignment/{assignmentId}/submit")
    public ResponseEntity<AssignmentGrades> handInAssignment(
            @PathVariable @NotNull Long assignmentId,
            @RequestParam @NotNull Long studentId,
            @RequestParam("file") MultipartFile file) {
        AssignmentGrades submission = studentQuizAssignmentService.handInAssignment(assignmentId, studentId, file);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/assignments/grades/{studentId}")
    public ResponseEntity<List<AssignmentGrades>> viewAssignmentsGrades(
            @PathVariable @NotNull Long studentId) {
        List<AssignmentGrades> grades = studentQuizAssignmentService.viewAssignmentsGrades(studentId);
        return grades != null ? ResponseEntity.ok(grades) : ResponseEntity.notFound().build();
    }

    @GetMapping("/quizzes/grades/{studentId}")
    public ResponseEntity<List<QuizGrades>> viewQuizzesGrades(
            @PathVariable @NotNull Long studentId) {
        List<QuizGrades> grades = studentQuizAssignmentService.viewQuizzesGrades(studentId);
        return grades != null ? ResponseEntity.ok(grades) : ResponseEntity.notFound().build();
    }

    @PostMapping("/quiz/submit")
    public ResponseEntity<QuizGrades> submitQuiz(
            @Valid @RequestBody QuizGrades submission) {
        QuizGrades submittedQuiz = quizService.submitQuiz(submission);
        return submittedQuiz != null ? ResponseEntity.ok(submittedQuiz) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/assignment/grade/{studentId}/{assignmentId}")
    public ResponseEntity<AssignmentGrades> viewAssignmentGrade(
            @PathVariable @NotNull Long studentId,
            @PathVariable @NotNull Long assignmentId) {
        AssignmentGrades assignmentGrades = studentQuizAssignmentService
                .ViewStudentAssignmentGrade(studentId, assignmentId);
        return assignmentGrades != null ? ResponseEntity.ok(assignmentGrades) : ResponseEntity.notFound().build();
    }
}
