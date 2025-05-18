package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ServiceLayer.QuizGradesService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz-grades")
@Validated
public class QuizGradesController {

    @Autowired
    private QuizGradesService gradesService;

    @PostMapping("/start")
    public ResponseEntity<QuizGrades> startQuiz(
            @RequestParam @NotNull(message = "Quiz ID is required") Long quizId,
            @RequestParam @NotNull(message = "Student ID is required") Long studentId) {

        QuizGrades attempt = gradesService.startQuizAttempt(quizId, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/{attemptId}/submit")
    public ResponseEntity<QuizGrades> submitQuiz(
            @PathVariable @NotNull(message = "Attempt ID is required") Long attemptId,
            @RequestBody @NotBlank(message = "Answers are required") String answers) {

        QuizGrades submission = gradesService.submitQuizAttempt(attemptId, answers);
        return submission != null ?
                ResponseEntity.ok(submission) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{attemptId}/grade")
    public ResponseEntity<QuizGrades> gradeQuiz(
            @PathVariable @NotNull(message = "Attempt ID is required") Long attemptId,
            @RequestParam String grade,
            @RequestParam String feedback) {
        QuizGrades graded = gradesService.gradeQuiz(attemptId, grade, feedback);
        return graded != null ?
                ResponseEntity.ok(graded) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<QuizGrades>> getStudentQuizGrades(@PathVariable @NotNull(message = "Student ID is required") Long studentId) {
        List<QuizGrades> grades = gradesService.getStudentQuizzesGrades(studentId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuizGrades>> getQuizGrades(@PathVariable @NotNull(message = "Quiz ID is required") Long quizId) {
        List<QuizGrades> grades = gradesService.getQuizGrades(quizId);
        return grades != null ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/searchByCourse")
    public ResponseEntity<List<QuizGrades>> getGradesByCourseName(@RequestParam @NotBlank(message = "Course name is required") String courseName) {
        List<QuizGrades> grades = gradesService.getGradesByCourseName(courseName);
        return grades != null && !grades.isEmpty() ?
                ResponseEntity.ok(grades) :
                ResponseEntity.notFound().build();
    }
}
