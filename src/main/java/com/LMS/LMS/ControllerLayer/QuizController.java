package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.DTO.QuizDetailsDTO;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ModelLayer.QuizGrades;
import com.LMS.LMS.ServiceLayer.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/CreateQuiz")
    public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(quiz));
    }

    @GetMapping
    public ResponseEntity<List<QuizDetailsDTO>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDetailsDTO> getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<QuizGrades> startQuiz(
            @PathVariable Long id,
            @RequestParam Long studentId) {
        QuizGrades attempt = quizService.startQuiz(id, studentId);
        return attempt != null ?
                ResponseEntity.ok(attempt) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<QuizGrades> submitQuiz(
            @PathVariable Long id,
            @RequestBody QuizGrades submission) {
        QuizGrades submittedQuiz = quizService.submitQuiz(submission);
        return submittedQuiz != null ?
                ResponseEntity.ok(submittedQuiz) :
                ResponseEntity.badRequest().build();
    }
}