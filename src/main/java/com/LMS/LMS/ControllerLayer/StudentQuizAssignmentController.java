package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.AssignmentGrades;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ServiceLayer.StudentQuizAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentQuizAssignmentController {

    @Autowired
    private StudentQuizAssignmentService studentQuizAssignmentService;

    @PostMapping("/quiz/{quizId}/take")
    public ResponseEntity<QuizGrades> takeQuiz(
            @PathVariable Long quizId,
            @RequestParam Long studentId) {
        QuizGrades attempt = studentQuizAssignmentService.takeQuiz(quizId, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/assignment/{assignmentId}/submit")
    public ResponseEntity<AssignmentGrades> handInAssignment(
            @PathVariable Long assignmentId,
            @RequestParam Long studentId,
            @RequestBody String submissionContent) {
        AssignmentGrades submission = studentQuizAssignmentService.handInAssignment(assignmentId, studentId, submissionContent);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.badRequest().build();
    }

    @GetMapping("/{studentId}/assignment-grades")
    public ResponseEntity<List<AssignmentGrades>> viewAssignmentGrades(
            @PathVariable Long studentId) {
        List<AssignmentGrades> grades = studentQuizAssignmentService.viewAssignmentGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/{studentId}/quiz-grades")
    public ResponseEntity<List<QuizGrades>> viewQuizGrades(
            @PathVariable Long studentId) {
        List<QuizGrades> grades = studentQuizAssignmentService.viewQuizGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}