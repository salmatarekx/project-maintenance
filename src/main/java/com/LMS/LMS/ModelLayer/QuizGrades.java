package com.LMS.LMS.ModelLayer;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class QuizGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User student;

    private String grade;
    private String feedback;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer attemptNumber;
    private String answers; // Could store JSON of answers

    // Constructors
    public QuizGrades() {}

    public QuizGrades(Integer id, Quiz quiz, User student, String grade,
                      String feedback, LocalDateTime startTime, LocalDateTime endTime,
                      Integer attemptNumber, String answers) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.grade = grade;
        this.feedback = feedback;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attemptNumber = attemptNumber;
        this.answers = answers;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Quiz getQuiz() { return quiz; }
    public void setQuiz(Quiz quiz) { this.quiz = quiz; }

    public User getStudent() { return student; }
    public void setStudent(User student) { this.student = student; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public Integer getAttemptNumber() { return attemptNumber; }
    public void setAttemptNumber(Integer attemptNumber) { this.attemptNumber = attemptNumber; }

    public String getAnswers() { return answers; }
    public void setAnswers(String answers) { this.answers = answers; }
}