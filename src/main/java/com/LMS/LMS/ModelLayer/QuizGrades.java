package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quiz_grades")
public class QuizGrades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @NotBlank(message = "Title cannot be blank")
    private Quiz title;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @NotBlank(message = "Grade cannot be blank")
    private String grade;

    @Size(max = 1000, message = "Feedback cannot exceed 1000 characters")
    private String feedback;

    @NotNull(message = "Start time must be provided")
    @PastOrPresent(message = "Start time cannot be in the future")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotNull(message = "End time must be provided")
    @PastOrPresent(message = "End time cannot be in the future")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @NotNull(message = "Attempt number must be provided")
    @Min(value = 1, message = "Attempt number must be at least 1")
    @Column(name = "attempt_number")
    private Long attemptNumber;

    @NotBlank(message = "Answers cannot be blank")
    private String answers;

    // Default constructor
    public QuizGrades() {}

    // Parameterized constructor
    public QuizGrades(Long id, Quiz quiz, Quiz title, User student, String grade,
                      String feedback, LocalDateTime startTime,
                      LocalDateTime endTime, Long attemptNumber,
                      String answers) {
        this.id = id;
        this.quiz = quiz;
        this.title = title;
        this.student = student;
        this.grade = grade;
        this.feedback = feedback;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attemptNumber = attemptNumber;
        this.answers = answers;
    }

    // Getters and Setters (corrected getGrade())
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Quiz getTitle(){
        return title;
    }

    public void setTitle(Quiz title){
        this.title = title;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(Long attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }
}
