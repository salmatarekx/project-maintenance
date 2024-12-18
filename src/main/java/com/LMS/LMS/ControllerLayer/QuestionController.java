package com.LMS.LMS.ControllerLayer;

import com.LMS.LMS.ModelLayer.Question;
import com.LMS.LMS.ModelLayer.Quiz;
import com.LMS.LMS.ServiceLayer.QuestionService;
import com.LMS.LMS.ServiceLayer.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/quiz/{quizId}")
    public ResponseEntity<Question> addQuestionToQuiz(
            @PathVariable Long quizId,
            @RequestBody Map<String, Object> questionData) {
        try {
            Quiz quiz = quizService.getQuiz(quizId).orElseThrow();

            Question question = new Question();
            question.setQuiz(quiz);
            question.setQuestionText((String) questionData.get("questionText"));
            question.setOptionA((String) questionData.get("optionA"));
            question.setOptionB((String) questionData.get("optionB"));
            question.setOptionC((String) questionData.get("optionC"));
            question.setOptionD((String) questionData.get("optionD"));
            question.setCorrectAnswer((String) questionData.get("correctAnswer"));
            question.setPoints((Integer) questionData.get("points"));

            Question savedQuestion = questionService.createQuestion(question);
            return ResponseEntity.ok(savedQuestion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuizQuestions(@PathVariable Long quizId) {
        try {
            Quiz quiz = quizService.getQuiz(quizId).orElseThrow();
            List<Question> questions = questionService.getQuizQuestions(quiz);
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long questionId) {
        try {
            questionService.deleteQuestion(questionId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}