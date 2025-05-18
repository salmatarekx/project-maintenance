package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @NotBlank(message = "Question text cannot be blank")
    private String questionText;

    @NotBlank(message = "Option A cannot be blank")
    private String optionA;

    @NotBlank(message = "Option B cannot be blank")
    private String optionB;

    @NotBlank(message = "Option C cannot be blank")
    private String optionC;

    @NotBlank(message = "Option D cannot be blank")
    private String optionD;

    @NotBlank(message = "Correct answer cannot be blank")
    @Pattern(regexp = "[ABCD]", message = "Correct answer must be one of: A, B, C, or D")
    private String correctAnswer;

    @NotNull(message = "Points must be provided")
    @Min(value = 1, message = "Points must be at least 1")
    private Integer points;

    // Default constructor
    public Question() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }
}
