package com.LMS.LMS.ModelLayer;

import jakarta.persistence.*;

@Entity
public class QuizGrades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Quiz quiz;

    @ManyToOne
    private User student;

    private String grades;

    private String feedback;


    public QuizGrades(Integer id, Quiz quiz, User student, String grades , String feedback) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.grades = grades;
        this.feedback = feedback;
    }

    public QuizGrades() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
